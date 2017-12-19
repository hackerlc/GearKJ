package org.joker.gear.ui.demo

import org.joker.gear.base.contract.presenter.PresenterDataWrapper

/**
 * Lottie
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/12/15
 */
class LottiePresenter(v: LottieContract.View) :
        PresenterDataWrapper<String, LottieContract.View>(v),
        LottieContract.Presenter<String> {
    override fun fetch() {
    }

    override fun refreshData() {
    }

    override fun getData(): String {
        return mData
    }

    override fun close() {
    }
}