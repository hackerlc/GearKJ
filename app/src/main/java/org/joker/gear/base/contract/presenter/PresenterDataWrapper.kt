package org.joker.gear.base.contract.presenter

import com.qianhua.market.net.helper.ThrowableOther
import org.joker.gear.base.contract.BaseContract
import org.joker.gear.net.helper.ThrowableNetWorkError

/**
 * 如果Presenter需要数据那么就定义mData类型，并且在合适的位置初始化
 * 一般是在继承之后的init{}中分配数据空间例如 init { mData = ArrayList() }
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/14
 */
open class PresenterDataWrapper<R : Any,V : BaseContract.BaseView>(v : V) : AbstractPresenter<V>(v) {
    lateinit var mData : R
    protected set
    /**
     * 处理数据
     */
    protected open fun processData(d : R) {}
    /**
     * 数据链出错后的处理
     * 首先根据异常类型判断，处理提示逻辑(这里处理的异常都是一般异常)
     * 然后调用onError由每个页面根据异常类型进行细节处理
     */
    protected open fun errorData(error : Throwable) {
        var errorStr: String? = when (error){
            is ThrowableOther -> {
//                mView.get()?.showToast(error.localizedMessage)
                error.localizedMessage
            }
            is ThrowableNetWorkError -> {
//                mView.get()?.showToast(error.localizedMessage)
//                mView.get()?.onError(error)
                error.localizedMessage
            }
            else -> {null}
        }
        if(errorStr != null){
            mView.get()?.showToast(errorStr)
        }
    }

}