package org.joker.gear.net.api

import io.reactivex.Flowable
import org.joker.gear.config.APIConfig
import org.joker.gear.entity.ResponseJson
import org.joker.gear.entity.TravelNoteBook
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * 提供接口调用方法
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/8
 */
interface TravelNotesApi {
    @GET(APIConfig.Companion.BASE_URL_BREADTRIP)
    fun getTravelNotesList(@Query("key") key: String, @Query("start") start: String,
                                    @Query("count") count: String, @Query("data_type") data_type: String):
            Flowable<ResponseJson<TravelNoteBook>>
}