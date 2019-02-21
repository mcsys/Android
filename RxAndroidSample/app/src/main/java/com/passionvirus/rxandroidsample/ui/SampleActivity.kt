package com.passionvirus.rxandroidsample.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit

class SampleActivity : AppCompatActivity() {
    private val TAG = SampleActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = arrayOf("1", "7", "2", "3", "6")
        val earlySource: Observable<String> = Observable.fromArray(*data)
            .take(4)
            .zipWith(
                Observable.interval(100L, TimeUnit.MILLISECONDS),
                BiFunction { item: String, _: Long -> item }
            )

        val lateSource: Observable<String> = Observable.just(data[4])
            .zipWith(
                Observable.interval(300L, TimeUnit.MILLISECONDS),
                BiFunction { item: String, _: Long -> item }
            )

        val source: Observable<String> = Observable.concat(earlySource, lateSource)
            .sample(300L, TimeUnit.MILLISECONDS)
//            .sample(300L, TimeUnit.MILLISECONDS, true)

        source.subscribe { Log.d(TAG, it) }

    }
}