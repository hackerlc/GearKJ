package org.joker.gear.ui.travelnote.list

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.trello.rxlifecycle2.LifecycleTransformer
import kotlinx.android.synthetic.main.activity_travel_notes.*
import kotlinx.android.synthetic.main.include_header_back.view.*
import org.joker.gear.R
import org.joker.gear.base.BasePActivity

/**
 * 游记列表
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/7
 */
class TravelNotesActivity : BasePActivity<TravelNotesPresenter>() ,TravelNotesView<Int>{

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
    }

    override fun updateUI(t: Int) {
    }

    override fun <R> getLifecycle2(): LifecycleTransformer<R> {
        return bindToLifecycle()
    }

    override fun <R> getLifecycleDestroy(): LifecycleTransformer<R> {
        return bindToLifecycleDestroy()
    }

}