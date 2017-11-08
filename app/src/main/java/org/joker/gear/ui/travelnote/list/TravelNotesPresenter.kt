package org.joker.gear.ui.travelnote.list

import gear.yc.com.gearlibrary.rxjava.helper.RxSchedulersHelper
import org.joker.gear.base.contract.AbstractPresenter
import org.joker.gear.net.api.ApiManager
import org.joker.gear.pojo.Query
import org.joker.gear.pojo.TravelNoteBook
import java.util.*

/**
 * 游记列表逻辑
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/7
 */
class TravelNotesPresenter(v: TravelNotesView) :
        AbstractPresenter<MutableList<TravelNoteBook.Books>,
                TravelNotesView> (v) {
    var query = Query("全国",1,10)
    var any = 1

    init {
        mData = ArrayList()
    }
    override fun fetch() {
        ApiManager.travelNotesApi
                .getTravelNotesList(query.query,query.getPageQ(),query.getCountQ(),"")
                .compose(mView.getLifecycleDestroy())
                .compose(RxSchedulersHelper.io_main())
                .subscribe({processData(it.data?.getBookses())},
                        {errorData("暂无数据")},
                        {mView.onDialog(false)})
    }

    override fun processData(d: MutableList<TravelNoteBook.Books>?) {
        if (d == null){
            errorData("暂无数据")
        } else {
            if (query.page == 1) {
                mData.clear()
            }
            mData.addAll(d)
            query.page=+query.count
            mView.updateUI()
        }
    }

    override fun errorData(error: String) {
        mView.showToast(error)
    }

    fun refreshData() {
        query.page = 1
        fetch()
    }

    override fun close() {
    }
}