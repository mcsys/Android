package com.passionvirus.productivesample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class CoroutineActivity: AppCompatActivity() {
    val TAG = CoroutineActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

/***
 * 아래 사이트를 참고합니다.
 * https://tourspace.tistory.com/151?category=797357
 * 
 * runBlocking 내부 코드가 완료되기 전에 다음을 수행하지 않는다.
 * job 을 join 한 경우, 은 모두 완료되기 전에 다음을 수행하지 않는다.
 * coroutineScope : 내부 로직이 모두 수행되기전까지 다음라인이 수행되지 않는다.
 *
 *
 *
 *
***/

        /*
        Log.d(TAG, "Start")
        GlobalScope.launch { // launch a new coroutine in background and continue
            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
            Log.d(TAG, "World!") // print after delay
        }
        Log.d(TAG, "Hello") // main thread continues while coroutine is delayed
        Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
        Log.d(TAG, "End")
        */

        /*
        runBlocking<Unit> {
            Log.d(TAG, "Start")
            GlobalScope.launch {
                delay(1000L)
                Log.d(TAG, "World!")
            }
            Log.d(TAG, "Hello")
            Thread.sleep(2000L)
            Log.d(TAG, "End")
        }
        Log.d(TAG, "End funtion")
        */

        /*
        runBlocking {
            Log.d(TAG, "Start")
            val job = GlobalScope.launch {
                delay(1000L)
                Log.d(TAG, "World!")
            }
            Log.d(TAG, "Hello")
            job.join()
            Log.d(TAG, "End")
        }
        */

        /*
        runBlocking {
            val jobs = List(10) {
                launch {
                    delay(1000L)
                    Log.d(TAG, "Do Job")
                }
            }
//            jobs.forEach { it.join() }
            Log.d(TAG, "End runBlock")
        }
        Log.d(TAG, "End funtion")
        */

        /*
        runBlocking {
            launch {
                delay(700L)
                Log.d(TAG, "Block 1")
            }
            Log.d(TAG, "Go coroutineScope")
            coroutineScope {
                launch {
                    delay(500L)
                    Log.d(TAG, "Block 2")
                }
                delay(100L)
                Log.d(TAG, "End coroutineScope")
            }
            Log.d(TAG, "End funtion")
        }
        */

        /*
        runBlocking {
            launch {
                doWorld()
            }
            Log.d(TAG, "Hello")
        }
        */

        /*
        runBlocking {
            GlobalScope.launch {
                repeat(1000) { i ->
                    Log.d(TAG, "I'm sleep : $i")
                    delay(500L)
                }
            }
            delay(1300L)
            Log.d(TAG, "End funtion")
        }
        */

        runBlocking {
            val job = launch {
                repeat(1000) { i ->
                    Log.d(TAG, "I'm sleep : $i")
                    delay(500L)
                }
            }
            delay(1300L)
            Log.d(TAG, "Cancel And Join")
            job.cancel()
            job.join()
            Log.d(TAG, "End Done")
        }











        /*
                runBlocking {
                    Log.d(TAG, "Start")
                    GlobalScope.launch { // launch a new coroutine in background and continue
                        delay(1000L)
                        Log.d(TAG, "World!")
                    }
                    Log.d(TAG, "Hello") // main thread continues here immediately
                    runBlocking {     // but this expression blocks the main thread
                        delay(2000L)  // ... while we delay for 2 seconds to keep JVM alive
                    }
                    Log.d(TAG, "End")
                }
                */

        /*
        runBlocking {
            //sampleStart
            val job = GlobalScope.launch { // launch a new coroutine and keep a reference to its Job
                delay(1000L)
                Log.d("TEST1234", "World!")
            }
            Log.d("TEST1234", "Hello")
            job.join() // wait until child coroutine completes
        }
        */

        /*
        runBlocking { // this: CoroutineScope
            launch {
                delay(200L)
                Log.d("TEST1234", "Task from runBlocking")
            }

            coroutineScope { // Creates a coroutine scope
                launch {
                    delay(500L)
                    Log.d("TEST1234", "Task from nested launch")
                }

                delay(100L)
                Log.d("TEST1234", "Task from coroutine scope") // This line will be printed before the nested launch
            }

            Log.d("TEST1234", "Coroutine scope is over") // This line is not printed until the nested launch completes
        }
        */

        /*
        runBlocking {
            launch { doWorld() }
            Log.d("TEST1234", "World!")
        }
        */

        /*
        runBlocking {
            repeat(100_000) {
                // launch a lot of coroutines
                launch {
                    delay(1000L)
                    Log.d("TEST1234", "Doing")
                }
            }
        }
        */

        /*
        runBlocking {
            //sampleStart
            Log.d("TEST1234", "Start")
            GlobalScope.launch {
                repeat(1000) { i ->
                    Log.d("TEST1234", "I'm sleeping $i ...")
                    delay(500L)
                }
            }
            delay(1300L) // just quit after delay
            Log.d("TEST1234", "Repeat...") //sampleEnd
        }
        */

        /*
        runBlocking {
            val job = launch {
                repeat(1000) { i ->
                    Log.d("TEST1234", "job: I'm sleeping $i...")
                    delay(500L)
                }
            }
            delay(1300L)
            Log.d("TEST1234", "main: I'm tired of waiting!")
            job.cancel()
            job.join()
            Log.d("TEST1234", "main: Now I can quit.")
        }
        */

        /*
        runBlocking {
            val startTime = System.currentTimeMillis()
            val job = launch(Dispatchers.Default) {
                var nextPrintTime = startTime
                var i = 0
                while (i < 5) {
                    if (System.currentTimeMillis() >= nextPrintTime) {
                        Log.d("TEST1234", "job: I'm sleeping ${i++} ...")
                        nextPrintTime += 500L
                    }
                }
            }
            delay(1300L)
            Log.d("TEST1234", "main: I'm tired of waiting!")
            job.cancelAndJoin()
            Log.d("TEST1234", "main: Now I can quit.")
        }
        */

        /*
        runBlocking {
            val startTime = System.currentTimeMillis()
            val job = launch(Dispatchers.Default) {
                var nextPrintTime = startTime
                var i = 0
                while (isActive) {
                    if (System.currentTimeMillis() >= nextPrintTime) {
                        Log.d("TEST1234", "job: I'm sleeping ${i++} ...")
                        nextPrintTime += 500L
                    }
                }
            }
            delay(1300L)
            Log.d("TEST1234", "main: I'm tired of waiting!")
            job.cancelAndJoin()
            Log.d("TEST1234", "main: Now I can quit.")
        }
        */

        /*
        runBlocking {
            val job = launch {
                try {
                    repeat(1000) { i ->
                        Log.d("TEST1234", "job: I'm sleeping $i ...")
                        delay(500L)
                    }
                } finally {
                    Log.d("TEST1234", "job: I'm running finally")
                }
            }
            delay(1300L)
            Log.d("TEST1234", "main: I'm tired of waiting!")
            job.cancelAndJoin()
            Log.d("TEST1234", "main: Now I can quit.")
        }
        */

        /*
        runBlocking {
            val job = launch {
                try {
                    repeat(1000) { i ->
                        Log.d("TEST1234", "job: I'm sleeping $i ...")
                        delay(500L)
                    }
                } finally {
                    Log.d("TEST1234", "job: I'm running finally")
                    delay(1000L)
                    Log.d("TEST1234", "job: And I've just delayed for 1 sec because I'm non-cancellable")
                }
            }
            delay(1300L)
            Log.d("TEST1234", "main: I'm tired of waiting!")
            job.cancelAndJoin()
            Log.d("TEST1234", "main: Now I can quit.")
        }
        */

        /*
        // kotlinx.coroutines.TimeoutCancellationException: Timed out waiting for 1300 ms
        runBlocking {
            withTimeout(1300L) {
                repeat(1000) { i ->
                    Log.d("TEST1234", "job: I'm sleeping $i ...")
                    delay(500L)
                }
            }
        }
        */

        /*
        runBlocking {
            val result = withTimeoutOrNull(1300L) {
                repeat(1000) { i ->
                    Log.d("TEST1234", "job: I'm sleeping $i ...")
                    delay(500L)
                }
                "Done"
            }
            Log.d("TEST1234", "Result is $result")
        }
        */


    }

    private suspend fun doWorld() {
        delay(1000L)
        Log.d(TAG, "World")
    }
}