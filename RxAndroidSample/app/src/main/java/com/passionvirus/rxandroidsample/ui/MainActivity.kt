package com.passionvirus.rxandroidsample.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.passionvirus.rxandroidsample.R
import com.passionvirus.rxandroidsample.databinding.ActivityMainBinding
import com.trello.rxlifecycle2.RxLifecycle.bindUntilEvent
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver


class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    private lateinit var binding: ActivityMainBinding
    // Avoid memory leak - Case 1
    /*
    private lateinit var mDisPosable: Disposable
    */
    // Avoid memory leak - Case 3
    private val mCompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Memory leak - Basic
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

        Observable
            .just("Hello world!!")
            .subscribe(binding::setS)
        */


        // Avoid memory leak - Case 1
        /*
        val observer = object : DisposableObserver<String>() {
            override fun onComplete() {
            }

            override fun onNext(s: String) {
                binding.s = s
            }

            override fun onError(e: Throwable) {
            }

        }

        mDisPosable = Observable.create(ObservableOnSubscribe<String> {
            it.onNext("Hello world!")
            it.onComplete()
        }).subscribeWith(observer)
        */

        // Avoid memory leak - Case 2
        /*
        Observable.create(ObservableOnSubscribe<String> {
            it.onNext("Hello world")
            it.onComplete()

        })
            // Set RxAppCompatActivity for use bindToLifecycle or bindUntilEvent
//            .compose(bindToLifecycle())
            .compose(bindUntilEvent(ActivityEvent.DESTROY))
            .subscribe(binding::setS)
        */

        // Avoid memory leak - Case 3
        val disposable = Observable.create(ObservableOnSubscribe<String> {
            it.onNext("Hello world")
            it.onComplete()
        }).subscribe(binding::setS)

        mCompositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Avoid memory leak - Case 1
        /*
        if(mDisPosable.isDisposed)
            mDisPosable.dispose()
        */

        // Avoid memory leak - Case 2
        if(mCompositeDisposable.isDisposed)
            mCompositeDisposable.dispose()
    }
}
