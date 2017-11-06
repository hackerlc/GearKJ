package org.joker.gear.base

import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import gear.yc.com.gearlibrary.manager.ActivityManager

/**
 * app内基类
 * app内所有类原则上都要继承这个类，这个类中集合了共有的方法、参数、初始化
 * 但要注意初始化的东西不要太多以免影响activity加载
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/1
 */
open abstract class BaseActivity : AppActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        initData()
    }

    /**
     * 主要想法是把初始化view的代码放到一起，所以在此定义这个方法，每个继承类都需要实现此方法
     * 并且这个方法会在initData()之前执行
     * @see initData
     */
    abstract fun initUI()

    /**
     * 初始化数据的代码统一存放位置，这个方法会在initUI()之后执行
     * @see initUI
     */
    abstract fun initData()

    /**
     * 退出应用可以调用此方法， ActivityManager 会储存所有的activity类并且关闭它们
     * 当然这只是退出应用的一种方式，还可以使用某个singTask启动方式的activity类，
     * 并把它放到栈底，当你要退出应用的时候只需要进入这个activity然后调用finish就可以了
     * 当你使用这种方式关闭，你要注意你的app中是否有singleInstance启动的activity，
     * 因为它并不能正确的让singleInstance启动的activity出栈
     *
     * 如果你不了解activity的启动方式，那么可以去这个app的manifest文件中寻找答案
     */
    fun exitApp() {
        ActivityManager.getInstance().clearAllActivity()
        System.exit(0)
    }

    /**
     * 在这里可以拦截返回事件并处理它们
     * 例如当你需要点击2次返回按钮退出应用的时候
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.repeatCount == 0){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                finishAfterTransition()
            } else {
                finish()
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}