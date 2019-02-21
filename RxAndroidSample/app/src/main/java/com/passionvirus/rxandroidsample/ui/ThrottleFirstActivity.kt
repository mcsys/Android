package com.passionvirus.rxandroidsample.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit

class ThrottleFirstActivity : AppCompatActivity() {
    private val TAG = ThrottleFirstActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = arrayOf("1", "2", "3", "4", "5", "6")
        val earlySource : Observable<String> = Observable.just(data[0])
            .zipWith(
                Observable.interval(100L, TimeUnit.MILLISECONDS),
                BiFunction{item: String, _: Long -> item}
            )

        val middleSource : Observable<String> = Observable.just(data[1])
            .zipWith(
                Observable.interval(300L, TimeUnit.MILLISECONDS),
                BiFunction { item: String, _:Long -> item }
            )

        val lateSource : Observable<String> = Observable.just(data[2], data[3], data[4], data[5])
            .zipWith(
                Observable.interval(100L, TimeUnit.MILLISECONDS),
                BiFunction { item: String, _:Long -> item }
            )

        val source : Observable<String> = Observable.concat(earlySource, middleSource, lateSource)
            .throttleFirst(200L, TimeUnit.MILLISECONDS)

        source.subscribe{ Log.d(TAG, it.toString()) }
    }
}