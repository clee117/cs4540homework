package com.example.rkjc.news_app_2;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotificationIntentService extends IntentService {

    public NotificationIntentService() {super("NotificationIntentService");}

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        Log.d(null, "Intent received: " + action);
        NotificationTask.executeTask(this, action);
    }
}
