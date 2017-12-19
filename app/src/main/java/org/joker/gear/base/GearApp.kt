package org.joker.gear.base

import android.app.Application

/**
 * 类说明
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/12/6
 */
/**
 * app全局初始化，以及自定义属性位置
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/6
 */
class GearApp : Application(){

    companion object {
        lateinit var instance : GearApp
            private set
            get
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        init()
    }

    private fun init(){

    }
}