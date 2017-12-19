package org.joker.gear.api.finder

import com.google.auto.service.AutoService
import org.yanex.takenoko.*
import java.io.File
import java.util.*
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.tools.Diagnostic.Kind.ERROR
import kotlin.collections.LinkedHashSet

/**
 * TestAnnotation
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/21
 */
@Target(AnnotationTarget.CLASS)
annotation class TestAnnotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class APIManager(val className: String = "APIManager")

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class APIService

/**
 *
 */
@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("org.joker.gear.api.finder.APIManager",
        "org.joker.gear.api.finder.APIService")
@SupportedOptions(TestAnnotationProcessor.KAPT_KOTLIN_GENERATED_OPTION_NAME)
class TestAnnotationProcessor : AbstractProcessor() {
    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

    private lateinit var mFiler: Filer
    private lateinit var mElementUtils: Elements

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment): Boolean {
        //根据指定的annotation筛选被标识的类、参数、方法...
        val apiManagerAnnotations = roundEnv.getElementsAnnotatedWith(APIManager::class.java)
        //不能为空
        if (apiManagerAnnotations.isEmpty()) return false

        //指定目标目录
        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME] ?: run {
            processingEnv.messager.printMessage(ERROR, "Can't find the target directory for generated Kotlin files.")
            return false
        }
        //文件内容
        var typeApiElement = apiManagerAnnotations.iterator().next().toTypeElementOrNull() ?: return false
        val apiManagerAnnotation = typeApiElement.getAnnotation(APIManager::class.java)
        val className = apiManagerAnnotation.className
        val generatedKtFile = kotlinFile(mElementUtils.getPackageOf(typeApiElement).qualifiedName.toString()) {
            objectDeclaration(className) {
                val apiServiceAnnotations = roundEnv.getElementsAnnotatedWith(APIService::class.java)
                apiServiceAnnotations.iterator().forEach {
                    var typeName = it.toTypeElementOrNull() ?: return@forEach
                    var annotation = typeName.getAnnotation(APIService::class.java)
                    property(typeName.simpleName.toString().firstUpperToLetter(),typeName.simpleName.toString()) {
//                        initializer("22222")
//                    receiverType(typeElement.qualifiedName.toString())
//                    delegate("333")
//                    getterExpression("this::class.java.simpleName")
//                    getterExpression(typeElement.qualifiedName.toString())
                    }
                }
            }

        }

        //生成文件
        File(kaptKotlinGeneratedDir, "${className}a.kt").apply {
            parentFile.mkdirs()
            writeText(generatedKtFile.accept(PrettyPrinter(PrettyPrinterConfiguration())))
        }

        return true
    }


    override fun init(p0: ProcessingEnvironment?) {
        super.init(p0)
        if (p0 != null) {
            mFiler = p0.filer
            mElementUtils = p0.elementUtils
        }
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        val types: LinkedHashSet<String> = LinkedHashSet()
        types.add(APIManager::class.java.canonicalName)
        types.add(APIService::class.java.canonicalName)
        return Collections.unmodifiableSet(types)
    }

    fun Element.toTypeElementOrNull(): TypeElement? {
        if (this !is TypeElement) {
            processingEnv.messager.printMessage(ERROR, "Invalid element type, class expected", this)
            return null
        }

        return this
    }

    fun String.firstUpperToLetter(): String{
        val array = this.toCharArray()
        array[0] = array[0] + 32
        return String(array)
    }
}