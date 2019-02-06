package com.passionvirus.rxandroidsample.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.passionvirus.rxandroidsample.ui.LoopActivity
import io.reactivex.Observable
import java.util.*


class LoopViewModel : AndroidViewModel {
    val TAG = LoopActivity::class.java.simpleName

    constructor(@NonNull application: Application) : super(application) {
    }

    private val samples = Arrays.asList("banana", "orange", "apple mango", "melon", "watermelon")
    var result : ObservableField<String> = ObservableField()

    fun loop() {
        for(s in samples) {
            if(s.contains("banana")) {
                result.set(s)
            }
        }
    }

    // RxJava 1.x
    fun loop2() {
        /*
        Observable.from(samples)
            .filter{ s -> s.contains("apple") }
            .firstOrDefault("Not Found")
            .subscribe { s -> { Log.d(TAG, s)} }
        */
        result.set("RxJava 1.x - Not Working")
    }

    // RxJava 2.x
    fun loop3() {
        Observable.fromIterable(samples)
            .filter{ s -> s.contains("apple") }
//            .skipWhile{ s -> !s.contains("apple") }
            .first("Not Found")
            .subscribe{ s -> run { result.set(s) } }
    }
}