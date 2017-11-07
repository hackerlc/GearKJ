package org.joker.gear.ui.travelnote.list

import org.joker.gear.base.contract.BaseContract

/**
 * 类说明
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/7
 */
class TravelNotesPresenter(override var mView: TravelNotesView<Int>) :
        BaseContract.BasePresenter<TravelNotesView<Int>>{

    var any = 1

    override fun fetch() {

    }

    fun refreshData() {}

    override fun close() {
    }
}