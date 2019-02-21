package com.passionvirus.rxandroidsample.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class DebounceActivity : AppCompatActivity() {
    private val TAG = DebuggingActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = arrayOf("1", "2", "3", "5")
        val source : Observable<String> = Observable.concat(
            Observable.interval(100L, TimeUnit.MILLISECONDS).map { i -> data[0] },
            Observable.interval(300L, TimeUnit.MILLISECONDS).map { i -> data[1] },
            Observable.interval(100L, TimeUnit.MILLISECONDS).map { i -> data[2] },
            Observable.interval(300L, TimeUnit.MILLISECONDS).map { i -> data[3] }
        )
            .debounce(200L, TimeUnit.MILLISECONDS)

        source.subscribe{ Log.d(TAG, it) }


    }
}