package com.qianhua.market.net.helper

/**
 * 对于调取接口成功但是数据为null的情况处理
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/12/8
 */
class ThrowableSuccess4DataNull(message: String?, cause: Throwable?): Throwable(message,cause){
    constructor(message: String?) : this(message, null)

    constructor(cause: Throwable?) : this(cause?.toString(), cause)

    constructor() : this(null, null)
}