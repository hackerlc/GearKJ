package org.joker.gear.ui.demo

import android.view.View
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
        animation_view.repeatCount = 0
        animation_view.playAnimation()
    }

    override fun attachPresenter() {
        mPresenter = LottiePresenter(this)
    }

    override fun initData() {
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