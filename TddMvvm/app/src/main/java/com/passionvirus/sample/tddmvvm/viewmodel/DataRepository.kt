package com.passionvirus.sample.tddmvvm.viewmodel

import com.passionvirus.sample.tddmvvm.AppExecutors
import com.passionvirus.sample.tddmvvm.db.AppDatabase

class DataRepository(database: AppDatabase, executors: AppExecutors){
    fun getUser() {
//        mDatabase.userDao().getLatestUser()
//        mExecutors.diskIO()?.execute { mDatabase.userDao().update(user) }
    }
}