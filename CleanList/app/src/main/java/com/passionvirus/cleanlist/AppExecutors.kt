package com.passionvirus.cleanlist

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class AppExecutors {

    companion object {
        private lateinit var diskIO : Executor
        private lateinit var networkIO : Executor
        private lateinit var mainThread : Executor

        private class MainThreadExecutor : Executor {
            private val mainThreadHandler = Handler(Looper.getMainLooper())
            override fun execute(command: Runnable) {
                mainThreadHandler.post(command)
            }
        }
    }

    private fun appExecutors(diskIOExecutor : Executor, networkIOExecutor : Executor, mainThreadExecutor : Executor) {
        diskIO = diskIOExecutor
        networkIO = networkIOExecutor
        mainThread = mainThreadExecutor
    }

    fun AppExecutors() {
        appExecutors(
            Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3),
            MainThreadExecutor())
    }

    fun diskIO() : Executor {
        return diskIO
    }

    fun networkIO() : Executor {
        return networkIO
    }

    fun mainThread() : Executor{
        return mainThread
    }
}