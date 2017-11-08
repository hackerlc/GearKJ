package org.joker.gear.pojo

import com.google.gson.annotations.SerializedName

/**
 * 返回数据接收
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/8
 */
open class ResponseJson<T> {
    @SerializedName("data")
    var data : T? = null

    var ret: String = ""
    var errcode: Int = 0
    var errmsg: String = ""
    var errMsg: String = ""
    var ver: String = ""
    var errNum: Int = 0
    //bread
    var status: Int = 0
    var message: String = ""

}