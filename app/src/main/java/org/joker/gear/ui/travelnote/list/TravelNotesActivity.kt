package org.joker.gear.ui.travelnote.list

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.trello.rxlifecycle2.LifecycleTransformer
import gear.yc.com.gearlibrary.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_travel_notes.*
import kotlinx.android.synthetic.main.include_header_back.view.*
import org.joker.gear.R
import org.joker.gear.base.activity.BasePActivity

/**
 * 游记列表
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/7
 */
class TravelNotesActivity : BasePActivity<TravelNotesPresenter>(),
        TravelNotesView{
    lateinit var adapter : TravelNotesAdapter

    @SuppressLint("ResourceAsColor")
    override fun initUI() {
        setContentView(R.layout.activity_travel_notes)
        srlRefresh.setColorSchemeColors(R.color.colorPrimary,R.color.colorPrimaryDark)
        srlRefresh.setOnRefreshListener { mPresenter.refreshData() }
        rvBooks.layoutManager = LinearLayoutManager(this)

        icTitle.tvTitle.text = "游记"
        icTitle.ivBack.visibility = View.INVISIBLE
    }

    override fun attachPresenter() {
        mPresenter = TravelNotesPresenter(this)
    }

    override fun initData() {
        adapter = TravelNotesAdapter(this, mPresenter.mData)
        rvBooks.adapter = adapter
        mPresenter.fetch()
    }

    override fun updateUI() {
        adapter.notifyDataSetChanged()
    }

    override fun showToast(str: String) {
        ToastUtil.getInstance().makeShortToast(this,str)
    }

    override fun onDialog(show: Boolean) {
        srlRefresh.isRefreshing = show
    }

    override fun <R> getLifecycle2(): LifecycleTransformer<R> {
        return bindToLifecycle()
    }

    override fun <R> getLifecycleDestroy(): LifecycleTransformer<R> {
        return bindToLifecycleDestroy()
    }

}