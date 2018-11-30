package com.example.rkjc.news_app_2;

import android.content.Context;
import android.support.annotation.NonNull;

import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

public class ScheduleUtils {
    private static final int SCHEDULE_INTERVAL_SECONDS = 10;
    private static final int FLEX_TIME_SECONDS = 10;
    private static final String REFRESH_TAG = "news_item_job";

    private static FirebaseJobDispatcher jobDispatcher;
    private static Job refreshJob;

    private static boolean initialized = false;

    synchronized public static void scheduleRefresh(@NonNull final Context context) {
        if(initialized) return;

        Driver driver = new GooglePlayDriver(context);
        jobDispatcher = new FirebaseJobDispatcher(driver);
        refreshJob = jobDispatcher.newJobBuilder()
                .setService(NewsItemJob.class)
                .setTag(REFRESH_TAG)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(SCHEDULE_INTERVAL_SECONDS,
                        SCHEDULE_INTERVAL_SECONDS + FLEX_TIME_SECONDS))
                .setReplaceCurrent(true)
                .build();

        jobDispatcher.schedule(refreshJob);
        initialized = true;
    }

    synchronized public static void stopRefresh() {
        if(initialized) {
            jobDispatcher.cancel(REFRESH_TAG);
            initialized = false;
        }
    }
}
