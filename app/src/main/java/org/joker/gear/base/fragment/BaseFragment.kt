package org.joker.gear.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 类说明
 * @author joker
 * Email:812405389@qq.com
 * @version 2017/12/6
 */
abstract class BaseFragment(id: Int): RxLifecycleFragment(), View.OnClickListener{
    val LAYOUT_RES_ID = id

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(LAYOUT_RES_ID, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachPresenter()
        initUI()
        initData()
    }

    /**
     * 初始化Presenter
     * @see initUI
     */
    abstract protected fun attachPresenter()
    /**
     * 主要想法是把初始化view的代码放到一起，所以在此定义这个方法，每个继承类都需要实现此方法
     * 并且这个方法会在initData()之前执行
     * @see initData
     */
    abstract protected fun initUI()


    /**
     * 初始化数据的代码统一存放位置，initUI()之后执行
     * @see attachPresenter
     */
    abstract protected fun initData()
}