package com.passionvirus.pvpush.service

import com.firebase.jobdispatcher.JobParameters
import com.firebase.jobdispatcher.JobService

class PVFirebaseJobService : JobService() {

    override fun onStartJob(jobParameters: JobParameters): Boolean {
        // add long running task here.
        return false
    }

    override fun onStopJob(jobParameters: JobParameters): Boolean {
        return false
    }

    companion object {
//        private val TAG = PVFirebaseJobService::class.simpleName
        private val TAG = "PVFirebaseJobService"
    }


}