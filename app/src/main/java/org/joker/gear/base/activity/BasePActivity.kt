package org.joker.gear.base.activity

import org.joker.gear.base.contract.BaseContract

/**
 * 给MVP结构的activity定义mPresenter变量类型，需要MVP的activity需要继承此类
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/7
 */
abstract class BasePActivity<T : BaseContract.BasePresenter<*>> : BaseActivity(){
    protected lateinit var mPresenter : T
}