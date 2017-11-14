package org.joker.gear.ui.travelnote.list

import org.joker.gear.base.contract.BaseContract

/**
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/14
 */
interface ContractTravelNotes {
    interface Presenter<out T> : BaseContract.BasePresenter<T>

    interface View : BaseContract.BaseView
}