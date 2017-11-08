package org.joker.gear.base.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import gear.yc.com.gearlibrary.network.http.OkHttpManager
import org.joker.gear.net.NetManager
import java.io.InputStream

/**
 * 这里会生成一个名为AGlide的类，可以在这里自定义Glide的各种加载方式
 * 建议在使用Glide时写一个这样的类，以免后期需要自定义时不方便
 * 这也是Glide V4 的新方法
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/8
 */
@GlideModule(glideName = "AGlide")
class AppGlideModule : AppGlideModule() {
    /**
     * glide加载的时候使用一个全局的OkHttp来访问网络
     */
    override fun registerComponents(context: Context?, glide: Glide?, registry: Registry?) {
        registry?.replace(GlideUrl::class.java,
                InputStream::class.java,
                OkHttpUrlLoader.Factory(NetManager.mClient))
    }
}