package com.example.drawer;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.jetbrains.annotations.NotNull;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
//    public MyFirebaseMessagingService() {
//        super();
//    }
//
//        @RequiresApi(api = Build.VERSION_CODES.O)
//        @Override
//    public void onMessageReceived(@NonNull @NotNull RemoteMessage remoteMessage) {
//        String title = remoteMessage.getNotification().getTitle();
//        String body = remoteMessage.getNotification().getBody();
//        final String CHANNEL_ID = "HEADS_UP_NOTIFICATION";
//            NotificationChannel channel = new NotificationChannel(
//                    CHANNEL_ID,"Heads up Notification",
//                    NotificationManager.IMPORTANCE_HIGH
//            );
//            getSystemService(NotificationManager.class).createNotificationChannel(channel);
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
////                 .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
//                .setContentTitle(title)
//                .setContentText(body)
//                    .setAutoCancel(true);
//            NotificationManagerCompat.from(this).notify(1, builder.build());
//            super.onMessageReceived(remoteMessage);
//
//        }
//    public void getFirebaseMessage(String title, String Msg){
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "firebaseChannel")
//                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
//                .setContentTitle(title)
//                .setContentText(Msg)
//                .setAutoCancel(true);
//        NotificationManagerCompat compat = NotificationManagerCompat.from(this);
//        compat.notify(101, builder.build());
//
//    }
}
