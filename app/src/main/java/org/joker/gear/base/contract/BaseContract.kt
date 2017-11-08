package org.joker.gear.base.contract

import com.trello.rxlifecycle2.LifecycleTransformer
import org.joker.gear.R

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
    interface BaseView {
        /**
         * 网络数据处理成功后更新界面显示
         */
        fun updateUI()

        /**
         * 显示提示信息
         * @param str 信息内容
         */
        fun showToast(str : String)

        /**
         * 显示加载框
         * @param show true 显示 | false 不显示
         */
        fun onDialog(show : Boolean)

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
    interface BasePresenter {
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