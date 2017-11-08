package org.joker.gear.net

import gear.yc.com.gearlibrary.network.api.GearHttpServiceManager
import gear.yc.com.gearlibrary.network.http.OkHttpManager
import okhttp3.OkHttpClient
import org.joker.gear.base.config.APIConfig
import retrofit2.Retrofit

/**
 * 网络访问管理
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/6
 */
class NetManager {
    /**
     * 初始化Ok引用和Retrofit引用
     */
    companion object {
        var mClient : OkHttpClient = OkHttpManager.getInstance()
                .setHeader("apikey","beae89ef686795322d5a3c48579875d5")
                .build()
                .client
        var mRetrofit : Retrofit = GearHttpServiceManager.build(APIConfig.BASE_URL,mClient)
    }
}
