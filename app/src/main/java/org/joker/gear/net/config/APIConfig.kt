package org.joker.gear.net.config

import org.joker.gear.BuildConfig

/**
 * 提供API接口地址，以及其他接口配置参数、方法
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/7
 */
class APIConfig {
    companion object {
        const val BASE_URL = BuildConfig.BASE_URL
        const val BASE_URL_BREADTRIP=BuildConfig.BASE_URL_BREADTRIP
        const val BASE_URL_BREADTRIP_DETIAL=BuildConfig.BASE_URL_BREADTRIP_DETIAL

    }
}