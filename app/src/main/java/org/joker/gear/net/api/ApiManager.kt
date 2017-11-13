package org.joker.gear.net.api

import org.joker.gear.net.NetManager

/**
 * API管理并提供接口引用
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/8
 */
object ApiManager {
    val travelNotesApi : TravelNotesApi = NetManager.mRetrofit.create(TravelNotesApi::class.java)
}