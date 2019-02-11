package com.passionvirus.rxandroidsample.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.passionvirus.rxandroidsample.R
import com.passionvirus.rxandroidsample.databinding.ActivityTimertaskBinding
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class TimerTaskActivity : AppCompatActivity() {

    private val TAG = TimerTaskActivity::class.java.simpleName
    private val DELAY = 0L
    private val PERIOD = 1000L
    private val mTimer = Timer()

    private var mCount = 11
    private val MILLISINFUTURE = 11 * 1000L
    private val COUNT_DOWN_INTERVAL = 1000L
    private lateinit var mCountDownTimer : CountDownTimer

    private lateinit var binding : ActivityTimertaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_timertask)

//        timerStart()

    }

    private fun timerStart() {
        mTimer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                Log.d(TAG, "Run")
            }
        }, DELAY, PERIOD)
    }

    private fun timerStop() {
        mTimer.cancel()
    }

    private fun initcounDownTask() {

        mCountDownTimer = object : CountDownTimer(MILLISINFUTURE, COUNT_DOWN_INTERVAL) {
            override fun onFinish() {
                Log.d(TAG, "finish")
            }

            override fun onTick(millisUntilFinished: Long) {
                Log.d(TAG, "${mCount--}")
            }

        }
    }

    private fun startPolling1() {
        val ob : Observable<String> = Observable.interval(3, TimeUnit.SECONDS)
            .flatMap { Observable.just("polling #1") }

        ob.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ Log.d(TAG, it)}
    }

    private fun startPolling2() {
        val ob2 : Observable<String> = Observable.just("polling #2")
            .repeatWhen { o -> o.delay(3, TimeUnit.SECONDS) }

        ob2.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ Log.d(TAG, it)}
    }
}