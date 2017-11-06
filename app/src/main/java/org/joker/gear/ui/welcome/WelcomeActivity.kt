package org.joker.gear.ui.welcome

import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import org.joker.gear.R
import org.joker.gear.base.BaseActivity
import kotlinx.android.synthetic.main.activity_welcome.*
import java.util.*
import kotlin.collections.RandomAccess

/**
 * 类说明
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/6
 */
class WelcomeActivity : BaseActivity(){

    var videos : MutableList<String> = ArrayList()
    private val RANDOM_BOUND = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initData() {
        videos.add("android.resource://$packageName/${R.raw.guide_1}")
        videos.add("android.resource://$packageName/${R.raw.guide_2}")

        cvv_videoData.palyVideo(Uri.parse(videos[Random().nextInt(RANDOM_BOUND)]))
    }

    override fun initUI() {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_welcome)
    }

}