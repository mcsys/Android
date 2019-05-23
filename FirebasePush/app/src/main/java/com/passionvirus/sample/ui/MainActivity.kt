package com.passionvirus.sample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.passionvirus.sample.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Analytics.getInstance(this).init(application)
        Analytics.getInstance(this).sendLog()

        if (intent.extras != null) {
            for (key in intent.extras!!.keySet()) {
                val value = intent.extras!!.get(key)
                Log.d("MainActivity: ", "Key: $key Value: $value")
            }
        }

        FirebaseMessaging.getInstance().subscribeToTopic("test")
                .addOnCompleteListener { task ->
                    var msg = "성공"
                    if (!task.isSuccessful) {
                        msg = "실패"
                    }
//                    Log.d(FragmentActivity.TAG, msg)
                    Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
                }
        // [END subscribe_topics]


        // Token
        // dcRN6DUpjqU:APA91bHAubS75O_IecB7beHCxygjdpfWRVia26KVXQ_hGxt4AjM0hNXvRzOgQ4jKs7wzPWnONDJ2pYSsKCR7jxP1JPE5EeidjiJKwXCkd1JOoLAdyBUZH7hciYiWhz2oUCY9bmHYzNuB
        // { "data": { "image": "https://static.pexels.com/photos/4825/red-love-romantic-flowers.jpg", "message": "Firebase Push Message Using API" "AnotherActivity": "True" }, "to" : "device id Or Device token" }
        Log.d("TEST1234", "T: "+ FirebaseInstanceId.getInstance().token)


        /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId  = getString(R.string.default_notification_channel_id);
            String channelName = getString(R.string.default_notification_channel_name);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class)
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW))
        }
        // If a notification message is tapped, any data accompanying the notification
        // message is available in the intent extras. In this sample the launcher
        // intent is fired when the notification is tapped, so any accompanying data would
        // be handled here. If you want a different intent fired, set the click_action
        // field of the notification message to the desired intent. The launcher intent
        // is used when no click_action is specified.
        //
        // Handle possible data accompanying notification message.
        // [START handle_data_extras]
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }
        // [END handle_data_extras]
        Button subscribeButton = findViewById(R.id.subscribeButton);
        subscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Subscribing to news topic");
                // [START subscribe_topics]
                FirebaseMessaging.getInstance().subscribeToTopic("news")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                String msg = getString(R.string.msg_subscribed);
                                if (!task.isSuccessful()) {
                                    msg = getString(R.string.msg_subscribe_failed);
                                }
                                Log.d(TAG, msg);
                                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                // [END subscribe_topics]
            }
        });
        Button logTokenButton = findViewById(R.id.logTokenButton);
        logTokenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get token
                String token = FirebaseInstanceId.getInstance().getToken();
                // Log and toast
                String msg = getString(R.string.msg_token_fmt, token);
                Log.d(TAG, msg);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        */
    }
}
