package com.passionvirus.rxandroidsample.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.passionvirus.rxandroidsample.R
import com.passionvirus.rxandroidsample.databinding.ActivityAsynctaskBinding
import io.reactivex.MaybeObserver
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class AsyncTaskActivity : AppCompatActivity() {

    private val TAG = AsyncTaskActivity::class.java.simpleName
    private lateinit var binding: ActivityAsynctaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_asynctask)

        initRxAsync()
    }

    private fun initRxAsync() {
        Observable.just("Hello", "rx", "world")
//            .reduce((x, y) -> x + " " + y)
            .reduce { x: String, y: String -> "$x $y" }
            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(getObserver())
            .subscribe(
                { binding.textView.text = it },
                { e -> Log.e(TAG, e.message) },
                { Log.i(TAG, "done") }
            )


    }

    private fun getObserver() : MaybeObserver<String> {
        return object: MaybeObserver<String> {
            override fun onSuccess(t: String) {
                binding.message = t
            }

            override fun onComplete() {
                Log.d(TAG, "done")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "error")
            }

        }
    }

}