package com.passionvirus.rxandroidsample.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.lang.NumberFormatException

class ExceptionActivity : AppCompatActivity() {
    private val TAG = ExceptionActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        val source: Observable<String> = Observable.create{
            it.onNext("1")
            it.onError(Exception("Some Error"))
            it.onNext("3")
            it.onComplete()
        }

        try {
//            source.subscribe{println()}
            source.subscribe{Log.d(TAG, it)}
        } catch (e: Exception) {
            Log.e(TAG, e.message)
        }
        */

        /*
        val grades = arrayOf("70", "88", "$100", "93", "83")
        val source = Observable.fromArray(*grades)
            .map{ data -> Integer.parseInt(data) }
            .onErrorReturn { e ->
                if(e is NumberFormatException) {
                    e.printStackTrace()
                }

                return@onErrorReturn -1
            }
        source.subscribe{data ->
            if (data < 0) {
                Log.e(TAG, "Wrong Data found!!")
                return@subscribe
            }
            Log.d(TAG, "Grade is $data")
        }
        */

        /*
        val grades = arrayOf("70", "88", "$100", "93", "83")
        val source = Observable.fromArray(*grades)
            .map { data -> Integer.parseInt(data) }

        source.subscribe(
            { data ->
                Log.d(TAG, "$data") },
            { e ->
                if(e is NumberFormatException) {
                    e.printStackTrace()
                }
                Log.e(TAG, "Wrong Data foung!!")
            }
        )
        */

        val salesData = arrayOf("100", "200", "A300")
        val onParseError = Observable.defer {
            Log.d(TAG, "send email to admiistrator")
            return@defer Observable.just(-1)
        }.subscribeOn(Schedulers.io())

        val source = Observable.fromArray(*salesData)
            .map(Integer::parseInt)
            .onErrorResumeNext(onParseError)

        source.subscribe{ data ->
            if (data < 0) {
                Log.e(TAG, "Wrong Data found!!")
                return@subscribe
            }
            Log.i(TAG, "Sales Data: $data")
        }



    }
}