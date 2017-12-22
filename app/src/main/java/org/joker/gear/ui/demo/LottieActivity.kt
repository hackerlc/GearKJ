package org.joker.gear.ui.demo

import android.view.View
import com.airbnb.lottie.LottieDrawable
import com.allenliu.versionchecklib.core.AllenChecker
import com.allenliu.versionchecklib.core.VersionDialogActivity
import com.allenliu.versionchecklib.core.VersionParams
import com.trello.rxlifecycle2.LifecycleTransformer
import gear.yc.com.gearlibrary.utils.ProgressDialogUtil
import gear.yc.com.gearlibrary.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_lottie.*
import org.joker.gear.R
import org.joker.gear.base.activity.BasePActivity

/**
 * Lottie
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version
 */
class LottieActivity :
        BasePActivity<LottieContract.Presenter<String>>(R.layout.activity_lottie),
        LottieContract.View {

    override fun initUI() {
        animation_view.setAnimation("hello-world.json")
        animation_view.repeatCount = LottieDrawable.INFINITE
        animation_view.playAnimation()
    }

    override fun attachPresenter() {
        mPresenter = LottiePresenter(this)
    }

    lateinit var versionBuilder: VersionParams.Builder
    override fun initData() {
        //测试更新功能
        versionBuilder = VersionParams.Builder()
                .setRequestUrl("http://www.baidu.com")
                .setCustomDownloadActivityClass(VersionDialogActivity::class.java)
                .setOnlyDownload(true)
                .setDownloadUrl("http://test-1251233192.coscd.myqcloud.com/1_1.apk")
                .setUpdateMsg("123")
                .setShowNotification(true)
                .setShowDownloadingDialog(true)
                .setShowDownLoadFailDialog(false)

        AllenChecker.startVersionCheck(this,versionBuilder.build())
    }

    override fun onClick(v: View) {
        when (v.id) {

        }
    }

    override fun updateUI() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showToast(str: String) {
        ToastUtil.getInstance().makeShortToast(this, str)
    }

    override fun onError(error: Throwable) {

    }

    override fun onDialog(show: Boolean) {
        if (show) {
            ProgressDialogUtil.getInstance().build(this).show()
        } else {
            ProgressDialogUtil.getInstance().dismiss()
        }
    }

    override fun <R> getLifecycle2(): LifecycleTransformer<R> {
        return bindToLifecycle()
    }

    override fun <R> getLifecycleDestroy(): LifecycleTransformer<R> {
        return bindToLifecycleDestroy()
    }
}