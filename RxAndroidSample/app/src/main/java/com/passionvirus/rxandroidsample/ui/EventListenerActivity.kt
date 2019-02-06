package com.passionvirus.rxandroidsample.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.passionvirus.rxandroidsample.R
import com.passionvirus.rxandroidsample.databinding.ActivityEventlistenerBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random


class EventListenerActivity : AppCompatActivity() {

    private val TAG = EventListenerActivity::class.java.simpleName
    private lateinit var binding: ActivityEventlistenerBinding

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_eventlistener)

        // Map
        /*
        getClickEventObservable()
            .map { s -> ("clicked") }
            .subscribe(getObserver())
        */

        // FlatMap
        /*
        getClickEventObservable()
            .map { Random.nextInt(10) }
            .flatMap { compareNumbers(it) }
            .subscribe(getObserver())
        */

        // Debounce
        /*
        getClickEventObservable()
            .debounce(1000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .map{getTime()}
            .subscribe(getObserverMessage())
        */

        // EditText Watcher
        getObserverText()
            .debounce(500, TimeUnit.MILLISECONDS)
            .filter{ s -> !TextUtils.isEmpty(s)}
            .observeOn(AndroidSchedulers.mainThread())
            .map(CharSequence::toString)
            .subscribeWith(getObserverMessage())


    }

    private fun getClickEventObservable(): Observable<View> {
        return Observable.create { e -> binding.btnButton.setOnClickListener(e::onNext) }

        /*
        return Observable.create(object : ObservableOnSubscribe<View> {
            override fun subscribe(e: ObservableEmitter<View>) {
                binding.btnButton.setOnClickListener(e::onNext)
            }

        })
        */
    }

    private fun getObserver() : DisposableObserver<String> {
        return object : DisposableObserver<String>() {
            override fun onNext(s: String) {
                Log.d(TAG, s)
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }
        }
    }

    private fun getObserverMessage() : DisposableObserver<String> {
        return object : DisposableObserver<String>() {
            override fun onNext(s: String) {
                binding.message = s
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }
        }
    }

    private fun getObserverText() : Observable<CharSequence> {
        return Observable.create{ e -> binding.searchbox.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let { e.onNext(it) }
            }

        })}
    }

    private fun compareNumbers(input: Int) : Observable<String> {
        return Observable.just(input)
            .flatMap {
                val random = Random
                val data = random.nextInt(10)
                Observable.just("local : $it, remote : $data, result = ${it==data}")
            }
    }

    private fun getTime() : String {
        val sdf = SimpleDateFormat("HH:mm:ss", Locale.KOREAN)

        return sdf.format(Calendar.getInstance().time)
    }



}