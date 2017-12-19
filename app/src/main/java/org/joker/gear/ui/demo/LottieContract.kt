package org.joker.gear.ui.demo

import org.joker.gear.base.contract.BaseContract

/**
 * Lottie
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version
 */
interface LottieContract {
    interface View : BaseContract.BaseView

    interface Presenter<T> : BaseContract.BasePresenter<T>
}