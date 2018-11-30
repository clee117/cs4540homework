package com.example.rkjc.news_app_2;

import android.content.Context;
import android.util.Log;

public class NotificationTask {

    private static final String ACTION_CANCEL_JOB = "cancelJob";

    public static void executeTask(Context context, String action) {
        NotificationUtils.clearNotifications(context);
        if(action.equals(ACTION_CANCEL_JOB)) {
            Log.d(null, "Refresh Job has been disabled.");
            ScheduleUtils.stopRefresh();
        }
    }
}
