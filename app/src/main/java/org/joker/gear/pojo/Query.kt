package org.joker.gear.pojo

/**
 * 类说明
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/8
 */
class Query(query: String,page: Int, count: Int) {
    var query = query
    var page = page
    var count = count

    /**
     * 查询page
     */
    fun getPageQ() : String{
        return "$page"
    }

    /**
     * 查询page
     */
    fun getCountQ() : String{
        return "$count"
    }
}