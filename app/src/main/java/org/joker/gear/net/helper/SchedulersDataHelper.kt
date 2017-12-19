package org.joker.gear.net.helper

import com.qianhua.market.net.helper.ThrowableOther
import com.qianhua.market.net.helper.ThrowableSuccess4DataNull
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.functions.Function
import org.joker.gear.entity.ResponseJson

/**
 * 网络数据拦截处理
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/8
 */
class SchedulersDataHelper {
    companion object {
        /**
         * 错误信息
         */
        private var errorMsg = ""

        /**
         * 判断数据是否返回成功，成功则返回数据元，不成功则返回错误提示信息
         * 关于@label的方式我不是特别的明白，直接看文档好了(http://kotlinlang.org/docs/reference/returns.html)
         */
        fun <T> handleResult(): FlowableTransformer<ResponseJson<T>, T> {
            return FlowableTransformer {
                it.flatMap(Function {
                    errorMsg = if(it.errcode !=0) {
                        it.message
                    } else {
                        if (it.data == null){
                            return@Function Flowable.error(ThrowableSuccess4DataNull(errorMsg))
                        } else {
                            return@Function createData(it.data)
                        }
                    }
                    // 这里也可以直接继承Throwable返回其他的错误信息交由Presenter或者Model处理
                    return@Function Flowable.error(ThrowableOther(errorMsg))
                })
            }
        }

        /**
         * 请求成功后创建并返回数据
         */
        private fun <T> createData(data: T): Flowable<T> {
            return Flowable.create({
                it.onNext(data)
                it.onComplete()
            },BackpressureStrategy.BUFFER)
        }
    }
}