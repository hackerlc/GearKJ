package org.joker.gear.base

import android.app.Application
import org.joker.gear.net.NetManager

/**
 * app全局初始化，，以及自定义属性位置
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/6
 */
class GearApp : Application(){

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init(){
    }
}