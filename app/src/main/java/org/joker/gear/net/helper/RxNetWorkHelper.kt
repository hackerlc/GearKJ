package org.joker.gear.net.helper

import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.FlowableTransformer
import org.joker.gear.base.GearApp

/**
 * 类说明
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/12/20
 */
class RxNetWorkHelper {
    companion object {
        fun <T> isNetWork(): FlowableTransformer<T, T> {
            return FlowableTransformer {
                it.doOnSubscribe{
                    if (!isNetworkConnected(GearApp.instance)) {
                        throw ThrowableNetWorkError("没有连接，请检查网络设置")
                    }
                }
            }
        }

        /**
         * 判断是否有网络连接
         * @param context
         * @return
         */
        private fun isNetworkConnected(context: Context): Boolean {
            val mConnectivityManager = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mNetworkInfo = mConnectivityManager.activeNetworkInfo
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable
            }
            return false
        }

    }
}