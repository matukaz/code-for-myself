package com.teddydeveloper.stardewvalleywiki.Notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.teddydeveloper.stardewvalleywiki.R;

public class NotificationsImpl  {
    private static int mNotificationId = 0;
    // Show notification

    public static void notify(String title, String description, Context baseContext) {
        notifyWithAction(title,description,baseContext,null);
    }

    // Show notification with onClick action - When user taps on notification, it brings up screen passed in intent parameter
    public static void notifyWithAction(String title, String description, Context baseContext, PendingIntent intent) {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(baseContext)
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle(title)
                        .setSound(alarmSound)
                        .setContentText(description);
        buildAndNotify(baseContext,mBuilder,intent);
    }

    private static void buildAndNotify(Context context, NotificationCompat.Builder mBuilder, PendingIntent intent){
        // Sets an ID for the notification
        mNotificationId += 1;
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        android.app.Notification notication = mBuilder.build();
        notication.defaults |= Notification.DEFAULT_VIBRATE;
        notication.defaults |= Notification.DEFAULT_SOUND;
        notication.defaults |= Notification.DEFAULT_LIGHTS;

        if(intent != null) {
            notication.contentIntent = intent;
        }

        // Builds the notification and issues it.
        notificationManager.notify(mNotificationId, notication);
    }
}
