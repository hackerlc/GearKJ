package org.joker.gear.base.contract.presenter

import org.joker.gear.base.contract.BaseContract
import java.lang.ref.WeakReference

/**
 * 初始化mView
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * mView修改为弱引用防止内存泄漏
 * @version 2017/12/19
 * @version 2017/11/8
 */
abstract class AbstractPresenter<V : BaseContract.BaseView>(v : V) {
    val mView : WeakReference<V> = WeakReference(v)
}