package org.joker.gear.base.contract

import com.trello.rxlifecycle2.LifecycleTransformer

/**
 * MVP Base Contract
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/7
 */
interface BaseContract {
    /**
     * MVP Base View
     */
    interface BaseView<in T> {
        /**
         * 网络数据处理成功后更新界面显示
         */
        fun updateUI(t : T)

        /**
         * 主要为Presenter提供Rx生命周期管理
         * 需要调用bindToLifecycle()或其他自定方法
         */
        fun <R : Any?> getLifecycle2() : LifecycleTransformer<R>

        fun <R : Any?> getLifecycleDestroy() : LifecycleTransformer<R>
    }

    /**
     * MVP Base Presenter
     */
    interface BasePresenter<V : BaseView<*>> {
        var mView : V
        /**
         * 加载数据
         */
        fun fetch()

        /**
         * 结束时关闭引用
         */
        fun close()
    }
}