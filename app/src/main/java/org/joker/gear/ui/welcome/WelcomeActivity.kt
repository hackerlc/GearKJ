package org.joker.gear.ui.welcome

import android.content.Intent
import android.net.Uri
import android.view.WindowManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_welcome.*
import org.joker.gear.R
import org.joker.gear.base.BaseActivity
import org.joker.gear.ui.travelnote.list.TravelNotesActivity
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * 启动页面，一般启动页面会有三秒钟左右的展示时间，展示时间内可以做一些耗时操作，
 * 但是要注意耗时操作的时间
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/6
 */
class WelcomeActivity : BaseActivity(){

    var videos : MutableList<String> = ArrayList()
    private val RANDOM_BOUND = 2
    private val TIME = 3000L

    override fun initData() {
        videos.add("android.resource://$packageName/${R.raw.guide_1}")
        videos.add("android.resource://$packageName/${R.raw.guide_2}")

        cvv_videoData.palyVideo(Uri.parse(videos[Random().nextInt(RANDOM_BOUND)]))
        loading()
    }
    override fun attachPresenter() {
    }
    override fun initUI() {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_welcome)
    }

    private fun loading(){
        Observable.timer(TIME, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycleDestroy())
                .subscribe({
                    startActivity(Intent(this, TravelNotesActivity::class.java))
                    finish()
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        cvv_videoData.stopPlayback()
    }

}