package org.joker.gear.base

import android.os.Bundle
import gear.yc.com.gearlibrary.manager.ActivityManager


/**
 * 类说明
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/3
 */
open class AppActivity : RxLifecycle() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityManager.getInstance().activities.add(this)
    }
}