package org.joker.gear.ui.travelnote.list

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.trello.rxlifecycle2.LifecycleTransformer
import gear.yc.com.gearlibrary.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_travel_notes.*
import kotlinx.android.synthetic.main.include_header_back.view.*
import org.joker.gear.R
import org.joker.gear.base.activity.BasePActivity
import org.joker.gear.entity.TravelNoteBook
import org.joker.gear.ui.demo.LottieActivity

/**
 * 游记列表
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/7
 */
class TravelNotesActivity :
        BasePActivity<ContractTravelNotes.Presenter<MutableList<TravelNoteBook.Books>>>(R.layout.activity_travel_notes),
        ContractTravelNotes.View {

    private lateinit var adapter : TravelNotesAdapter

    /**
     * 初始化界面，使用Kotlin extensions 绑定控件
     */
    @SuppressLint("ResourceAsColor")
    override fun initUI() {
        srlRefresh.setColorSchemeColors(R.color.colorPrimary,R.color.colorPrimaryDark)
        srlRefresh.setOnRefreshListener { mPresenter.refreshData() }
        rvBooks.layoutManager = LinearLayoutManager(this)
        //因为布局中有include标签，直接调用被include布局的id会出现问题，所以就给include指定了一个id=icTitle
        icTitle.tvTitle.text = "游记"
        icTitle.ivBack.visibility = View.INVISIBLE

        fabSearch.setOnClickListener(this)
    }

    /**
     * 初始化Presenter，类型已经通过泛型指定
     */
    override fun attachPresenter() {
        mPresenter = TravelNotesPresenter(this)
    }

    /**
     * activity中不会持有数据引用，一切交互都由接口确定
     */
    override fun initData() {
        adapter = TravelNotesAdapter(this, mPresenter.getData())
        rvBooks.adapter = adapter
        mPresenter.fetch()
    }

    /**
     * 刷新界面
     */
    override fun updateUI() {
        adapter.notifyDataSetChanged()
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.fabSearch -> startActivity(Intent(this,LottieActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.close()
    }

    override fun showToast(str: String) {
        ToastUtil.getInstance().makeShortToast(this,str)
    }

    override fun onError(error: Throwable) {

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