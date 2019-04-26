package com.passionvirus.sample.tddmvvm.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class AppDatabase : RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "TddMvvm.db"

        private lateinit var instance: AppDatabase

        fun getInstance(context: Context): AppDatabase {
            synchronized(AppDatabase::class.java) {
                if (!::instance.isInitialized) {
                    instance = buildDatabase(context.applicationContext)
                }
            }

            return instance
        }

        private fun buildDatabase(appContext: Context): AppDatabase {
            return Room.databaseBuilder(appContext, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}

