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
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
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