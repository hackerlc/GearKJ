package org.joker.gear.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

/**
 * app内基类
 * app内所有类原则上都要继承这个类，这个类中集合了共有的方法、参数、初始化
 * 但要注意初始化的东西不要太多以免影响activity加载
 *
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/1
 */
class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}