package org.joker.gear.ui.travelnote.list

import gear.yc.com.gearlibrary.rxjava.helper.RxSchedulersHelper
import org.joker.gear.base.contract.presenter.PresenterDataWrapper
import org.joker.gear.config.AppConfig
import org.joker.gear.net.api.ApiManager
import org.joker.gear.entity.Query
import org.joker.gear.entity.TravelNoteBook
import java.util.*

/**
 * 游记列表逻辑
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/7
 */
class TravelNotesPresenter(v: ContractTravelNotes.View) :
        PresenterDataWrapper<MutableList<TravelNoteBook.Books>, ContractTravelNotes.View>(v),
        ContractTravelNotes.Presenter<MutableList<TravelNoteBook.Books>>{

    var query = Query(AppConfig.INFO_QUERY_ADDRESS,1,10)
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
                        {errorData(AppConfig.INFO_ERROR_NOT_DATA)},
                        {mView.onDialog(false)})
    }

    override fun processData(d: MutableList<TravelNoteBook.Books>?) {
        if (d == null){
            errorData(AppConfig.INFO_ERROR_NOT_DATA)
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

    override fun refreshData() {
        query.page = 1
        fetch()
    }

    override fun getData(): MutableList<TravelNoteBook.Books> {
        return mData
    }

    override fun close() {
    }
}