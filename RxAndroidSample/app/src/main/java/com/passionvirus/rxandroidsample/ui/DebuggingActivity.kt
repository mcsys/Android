package com.passionvirus.rxandroidsample.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.toObservable
import io.reactivex.rxkotlin.zipWith
import java.util.*
import java.util.concurrent.TimeUnit


class DebuggingActivity : AppCompatActivity() {
    private val TAG = DebuggingActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        val orgs = arrayOf("1", "3", "5")
        val source = Observable.fromArray(orgs)
        source.doOnNext { data -> Log.d(TAG, "onNext() : $data") }
            .doOnComplete { Log.d(TAG, "onComplete()") }
            .doOnError { e -> Log.e(TAG, "onError() : ${e.message}") }
            .subscribe(::println)
        */

        /*
        val array: Array<String> = arrayOf("A", "B", "C")
        val src: Observable<String> = array.toObservable()
        src.subscribe(::println)
        */

        // onError 확인
        /*
        val divider = arrayOf(10, 5, 0)
        val source = Observable.fromArray(*divider)
            .map { div -> 1000 / div }
            .doOnNext { data -> Log.d(TAG, "onNext() : $data") }
            .doOnComplete { Log.d(TAG, "onComplete()") }
            .doOnError { e -> Log.e(TAG, "onError() : ${e.message}") }
            .subscribe(::println)
        */

        // 객체의 이벤트별 처리
        /*
        val data = arrayOf("ONE", "TWO", "THREE")
        val source = Observable.fromArray(*data)
        source.doOnEach{noti ->
            if (noti.isOnNext) {
                Log.d(TAG, "onNext() = ${noti.value}")
            }
            if (noti.isOnComplete)
                Log.d(TAG, "onComplete()")
            if (noti.isOnError)
                Log.d(TAG, "onError() = ${noti.error!!.message}")
        }//.subscribe(::println)
            .subscribe{println()}
        */

        /*
        val data = arrayOf("1", "3", "5")
        val source = Observable.fromArray(*data)
        source.doOnEach(object: Observer<String> {
            override fun onComplete() {
                Log.d(TAG, "onComplete()")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: String) {
                Log.d(TAG, "onNext() = $t")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError() = ${e.message}")
            }
        }).subscribe{println()}
        */

        /*
        val orgs = arrayOf("1", "3", "5", "2", "6")
        val source = Observable.fromArray(*orgs)
        source.zipWith(
            Observable.interval(100L, TimeUnit.MILLISECONDS),
            BiFunction { item: String, _: Long -> item }
        )
            .doOnSubscribe { Log.d(TAG, "doOnSubscribe") }
            .doOnDispose { Log.d(TAG, "doOnDispose") }

        val d = source.subscribe(::println)

        Thread.sleep(200)
        d.dispose()
        Thread.sleep(300)
        */

        /*
        val numbers = Observable.range(1,4)
        val result = source.zipWith( numbers ) { s, n -> "$s $n"}
        result.subscribe(::println)
        */

        val orgs = arrayOf("1", "3", "5")
        val source = Observable.fromArray(*orgs)
        source.doOnTerminate { Log.d(TAG, "onTerminate()") }
            .doOnComplete { Log.d(TAG, "onComplete()") }
            .doOnError { Log.d(TAG, "onError") }
            .subscribe{ Log.d(TAG, it) }


    }
}