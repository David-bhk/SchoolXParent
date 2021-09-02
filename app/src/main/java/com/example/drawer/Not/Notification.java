package com.example.drawer.Not;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawer.AdminPanel.MainAdminPanelActivity;
import com.example.drawer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {
    RecyclerView notification_recycler_view;
    NotificationModel notificationModel;
    ArrayList<NotificationModel> notificationModelList;
    NotificationAdapter notificationAdapter;
    String notificationId;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Notifications");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        notification_recycler_view = findViewById(R.id.notification_recycler_view);
        Intent intent = getIntent();
        notificationId = intent.getStringExtra("notificationId");


        notification_recycler_view.setHasFixedSize(true);
        notification_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        notificationModelList = new ArrayList<>();
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading your Notifications");
        dialog.show();
        createNotificationChannel();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    notificationModel = dataSnapshot.getValue(NotificationModel.class);
                    notificationModelList.add(notificationModel);
                }
                if (notificationId == null) {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(Notification.this, "121")
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle(notificationModel.title)
                            .setContentText(notificationModel.content)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setAutoCancel(true);
                    Intent intent = new Intent(Notification.this, Notification.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("notificationId", notificationModel.id);
                    PendingIntent pendingIntent = PendingIntent.getActivity(Notification.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(pendingIntent);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(Notification.this);
                    notificationManager.notify(0, builder.build());


                }

                notificationAdapter = new NotificationAdapter(Notification.this, notificationModelList);
                notification_recycler_view.setAdapter(notificationAdapter);
                notificationAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

//        FirebaseMessaging.getInstance().subscribeToTopic("weather")
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        String msg = "Done";
//                        if (!task.isSuccessful()) {
//                            msg = "Failed";
//                            return;
//                        }
//
//                    }
//                });


    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_name);
            String description = getString(R.string.project_id);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("121", name, importance);
            channel.setDescription("this is a trial");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            // notificationManager.notify();
        } else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(Notification.this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("notificationModel.title")
                    .setContentText("notificationModel.content")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);
            Intent intent = new Intent(Notification.this, MainAdminPanelActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("notificationId", "notificationModel.id");
            PendingIntent pendingIntent = PendingIntent.getActivity(Notification.this, 0, intent, 0);
            builder.setContentIntent(pendingIntent);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(Notification.this);
            notificationManager.notify(0, builder.build());
        }
    }
}