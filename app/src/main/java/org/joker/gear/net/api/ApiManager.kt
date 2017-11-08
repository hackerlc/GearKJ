package org.joker.gear.net.api

import org.joker.gear.net.NetManager

/**
 * 类说明
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/8
 */
object ApiManager {
    var travelNotesApi : TravelNotesApi = NetManager.mRetrofit.create(TravelNotesApi::class.java)
    private set
}