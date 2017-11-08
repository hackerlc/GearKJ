package org.joker.gear.pojo

import com.google.gson.annotations.SerializedName

/**
 * 类说明
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/8
 */
class TravelNoteBook{
    @SerializedName("books")
    var mBookses : MutableList<Books>? = null
    @SerializedName("trips")
    var mTrips : MutableList<Books>? = null

    fun getBookses() : MutableList<Books>? {
        if (mBookses == null) {
            return mTrips
        }
        return mBookses
    }

    class Books{
        @SerializedName("title")
        var title = ""
        @SerializedName("bookUrl")
        var html = ""
        @SerializedName("headImage")
        var imgUrl = ""

        @SerializedName("id")
        var id = ""
        @SerializedName("cover_image_default")
        var coverImageDefault = ""
        @SerializedName("name")
        var name = ""
    }
}