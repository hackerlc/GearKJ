package org.joker.gear.base.contract

/**
 * 初始化mView，定义mData类型，并且需要初始化
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/8
 */
abstract class AbstractPresenter<R : Any,V : BaseContract.BaseView>(v : V) :
        BaseContract.BasePresenter{
    var mView = v
    lateinit var mData : R
    /**
     * 处理数据
     */
    protected open fun processData(d : R?) {}
    /**
     * 数据出错
     */
    protected open fun errorData(error : String) {}
}