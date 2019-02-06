package com.passionvirus.rxandroidsample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.passionvirus.rxandroidsample.R
import com.passionvirus.rxandroidsample.databinding.ActivityMainBinding
import io.reactivex.Observable


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        /*
        val observer = object : DisposableObserver<String>() {

            override fun onNext(s: String) {
                binding.s = s
            }

            override fun onError(e: Throwable) {}

            override fun onComplete() {
            }
        }

        Observable.create(ObservableOnSubscribe<String> {
            it.onNext("Hello world!")
            it.onComplete()
        }).subscribe(observer)

        Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(e: ObservableEmitter<String>) {
                e.onNext("Hello world!")
                e.onComplete()
            }

        })
        */

        Observable
            .just("Hello world!!")
            .subscribe(binding::setS)
    }

}
