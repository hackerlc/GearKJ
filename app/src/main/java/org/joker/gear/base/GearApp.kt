package org.joker.gear.base

import android.app.Application
import android.content.Context


/** app全局初始化，以及自定义属性位置
 * @author joker
 * Email:812405389@qq.com
 * @version 2017/11/6
 */
class GearApp : Application() {
    companion object {
        lateinit var instance: GearApp
            private set
            get

    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}