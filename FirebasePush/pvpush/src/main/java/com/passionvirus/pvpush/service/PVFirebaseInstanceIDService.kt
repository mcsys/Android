package com.passionvirus.pvpush.service

import android.util.Log
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.FirebaseMessagingService


class PVFirebaseInstanceIDService : FirebaseMessagingService() {

    companion object {
        private val TAG = PVFirebaseInstanceIDService::class.simpleName
    }

    override fun onNewToken(s: String?) {
        super.onNewToken(s)
        Log.d(TAG, "NEW_TOKEN : " + s!!)
        Log.d("TEST1234", "onNewToken")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        Log.d("TEST1234", "onMessageReceived")
    }


}
/*
public class PVFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = PVFirebaseInstanceIDService.class.simpleName
    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().token
        sendRegistrationToServer(refreshedToken);
    }
    private void sendRegistrationToServer(String token) {
    }
}
*/