package org.joker.gear.base.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.RxLifecycle
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.RxLifecycleAndroid
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * 如果你使用了RxJava+Retrofit，那么在管理RxJava的生命周期时可以采用这种方式
 * 也可以直接查看https://github.com/trello/RxLifecycleActivity
 * @author joker
 * Email:lc@shandaichaoren.com or 812405389@qq.com
 * @version 2017/11/3
 */
abstract class RxLifecycleActivity : AppCompatActivity(), LifecycleProvider<ActivityEvent> {
    private val lifecycleSubject : BehaviorSubject<ActivityEvent> = BehaviorSubject.create()

    override fun <T : Any?> bindUntilEvent(event: ActivityEvent): LifecycleTransformer<T> {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event)
    }

    override fun lifecycle(): Observable<ActivityEvent> {
        return lifecycleSubject.hide()
    }

    override fun <T : Any?> bindToLifecycle(): LifecycleTransformer<T> {
        return RxLifecycleAndroid.bindActivity<T>(lifecycleSubject)
    }

    fun <T : Any?> bindToLifecycleDestroy() : LifecycleTransformer<T> {
        return  RxLifecycle.bindUntilEvent(lifecycleSubject, ActivityEvent.DESTROY)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleSubject.onNext(ActivityEvent.CREATE)
    }

    override fun onStart() {
        super.onStart()
        lifecycleSubject.onNext(ActivityEvent.START)
    }

    override fun onResume() {
        super.onResume()
        lifecycleSubject.onNext(ActivityEvent.RESUME)
    }

    override fun onPause() {
        super.onPause()
        lifecycleSubject.onNext(ActivityEvent.PAUSE)
    }

    override fun onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP)
        super.onStop()

    }

    override fun onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY)
        super.onDestroy()
    }

}