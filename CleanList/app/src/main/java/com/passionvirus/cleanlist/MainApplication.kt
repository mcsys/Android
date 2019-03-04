package com.passionvirus.cleanlist

import android.app.Application

class MainApplication : Application() {

    lateinit var mAppExecutors : AppExecutors

    override fun onCreate() {
        super.onCreate()

        if(!::mAppExecutors.isInitialized) {
            mAppExecutors = AppExecutors()
        }
    }

    /*
    fun getDatabase(): AppDatabase {
        return AppDatabase.getInstance(this, mAppExecutors)
    }

    fun getRepository(): DataRepository {
        return DataRepository.getInstance(getDatabase())
    }
    */
}
