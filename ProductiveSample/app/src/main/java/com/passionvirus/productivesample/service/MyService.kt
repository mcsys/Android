package com.passionvirus.productivesample.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        throw UnsupportedOperationException("Not yet imp")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(MyService::class.java.name, "Do something")
        return START_STICKY
        // START_NOT_STICKY : 전달할 인텐트 제외 서비스가 중단되면 재생성하지 않는다 - 가장 일반적인 케이스
        // START_STICKY : 서비스가 중단되면 재시작 인텐트는 전달하지 않는다 - 미디어 플레이어 같은 서비스에 적합
        // START_REDELIVER_INTENT : 인텐트와 함께 서비스를 재시작 - 파일 다운로드에 적합
    }

}