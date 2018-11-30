package com.example.rkjc.news_app_2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.content.ContextCompat;

public class NotificationUtils {
    private static final int NOTIFICATION_ID = 1234;
    private static final int PENDING_INTENT_ID = 5678;
    private static final String NOTIFICATION_CHANNEL = "news_item_notification_channel";

    private static final int CANCEL_JOB_INTENT_ID = 1;

    public static void sendNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    NOTIFICATION_CHANNEL,
                    context.getString(R.string.main_channel_name),
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_arrow_downward_black_24dp)
                .setLargeIcon(makeLargeIcon(context))
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(context.getString(R.string.notification_text))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(context.getString(R.string.notification_text)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(true)
                .addAction(cancelJob(context));

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
    }

    public static void clearNotifications(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.cancel(NOTIFICATION_ID);
    }

    private static Action cancelJob(Context context) {
        Intent cancelJobIntent = new Intent(context, NotificationIntentService.class);
        cancelJobIntent.setAction("cancelJob");

        PendingIntent cancelJobPendingIntent = PendingIntent.getService(
                context,
                CANCEL_JOB_INTENT_ID,
                cancelJobIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        Action cancelJobAction = new Action(R.drawable.ic_clear_black_24dp, "Cancel", cancelJobPendingIntent);
        return cancelJobAction;
    }

    private static Bitmap makeLargeIcon(Context context) {
        Resources res = context.getResources();

        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.ic_disc_full_black_24dp);
        return largeIcon;
    }

    private static PendingIntent contentIntent(Context context) {
        Intent startIntent = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(
                context,
                PENDING_INTENT_ID,
                startIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }
}
