package com.example.jakub.pwrstproject.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.net.URI;

import com.example.jakub.pwrstproject.Constants;
import com.example.jakub.pwrstproject.R;
import com.example.jakub.pwrstproject.ui.MainActivity;


/**
 * Created by Jakub on 02.08.2016.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent intent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Intent replyIntent = new Intent(this, BackgroundSending.class);
        replyIntent.putExtra(Constants.USER_TARGET,remoteMessage.getData().get("username"));
        PendingIntent replyHodor = PendingIntent.getService(this, 1, replyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.jeszczeNieWiemAleBędzie);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle("PAUZA")
                        .setContentText(remoteMessage.getData().get("username"))
                        .addAction(R.drawable.ic_call_missed_outgoing_black_24dp, "POTWIERDŹ", replyHodor)
                        //.setSound(sound)
                        .setContentIntent(intent);

        NotificationManager mNotificationManager =
                                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(122412, mBuilder.build());


    }


}
