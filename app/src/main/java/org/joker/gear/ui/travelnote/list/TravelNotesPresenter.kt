package org.joker.gear.ui.travelnote.list

import gear.yc.com.gearlibrary.rxjava.helper.RxSchedulersHelper
import org.joker.gear.base.contract.presenter.PresenterDataWrapper
import org.joker.gear.config.AppConfig
import org.joker.gear.entity.Query
import org.joker.gear.entity.TravelNoteBook
import org.joker.gear.net.api.ApiManager
import org.joker.gear.net.helper.RxNetWorkHelper
import org.joker.gear.net.helper.SchedulersDataHelper
import java.util.*
import kotlin.collections.HashMap


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

    init {
        mData = ArrayList()
    }

    /**
     * 这里主要是获取网络数据，其实应该是Model的业务逻辑，暂时先放到了Presenter中
     * 获取网络数据的方式是由Retrofit+Rxjava的方式，并且自定义了两个处理组件 RxSchedulersHelper 和 SchedulersDataHelper
     * RxSchedulersHelper 主要是合并了ob.subscribeOn(Schedulers.io()) 和 observeOn(AndroidSchedulers.mainThread()) 操作
     * SchedulersDataHelper 去处理数据是否获取成功或者服务器返回的非成功标识，成功则返回数据，错误则返回错误信息
     * Rxjava的生命周期由mView.getLifecycleDestroy()绑定到Activity上
     */
    override fun fetch() {
        ApiManager.travelNotesApi
                .getTravelNotesList(query.query,query.getPageQ(),query.getCountQ(),"")
                .compose(RxNetWorkHelper.isNetWork())
                .compose(mView.get()!!.getLifecycleDestroy())
                .compose(RxSchedulersHelper.io_main())
                .compose(SchedulersDataHelper.handleResult())
                .subscribe({processData(it.getBookses())},
                        {errorData(it)},
                        {mView.get()?.onDialog(false)})

    }


    fun processData(d: MutableList<TravelNoteBook.Books>?) {
        if(mView.get() != null) {
            if (d == null) {
                mView.get()!!.showToast(AppConfig.INFO_ERROR_NOT_DATA)
            } else {
                if (query.page == 1) {
                    mData.clear()
                }
                mData.addAll(d)
                query.page = +query.count
                mView.get()!!.updateUI()
            }
        }
    }

    override fun errorData(error: Throwable) {
        super.errorData(error)
        mView.get()?.onError(error)
    }

    override fun refreshData() {
        query.page = 1
        fetch()
    }

    override fun getData(): MutableList<TravelNoteBook.Books> {
        return mData
    }

    override fun close() {
        mView.clear()
    }
}