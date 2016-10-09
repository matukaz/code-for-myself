package com.teddydeveloper.stardewvalleywiki.Notifications;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Matu on 03.10.2016.
 * https://firebase.google.com/docs/notifications/android/console-audience?utm_source=studio#receive_and_handle_notifications
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        showNotification(remoteMessage);
    }

    public void showNotification(RemoteMessage remoteMessage) {
        // If we have a link in extra data then make it clickable.
        if(remoteMessage.getData().containsKey("link")){
            Intent notificationIntent = new Intent(Intent.ACTION_VIEW);
            notificationIntent.setData(Uri.parse(remoteMessage.getData().get("link")));
            PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(),0,notificationIntent,0);
            NotificationsImpl.notifyWithAction(remoteMessage.getNotification().getTitle()+ "1", remoteMessage.getNotification().getBody(), getBaseContext(), pendingIntent);
        } else {
            NotificationsImpl.notify(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody() +remoteMessage.getData().containsKey("link"), getBaseContext());
        }
    }
}