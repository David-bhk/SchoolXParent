package com.example.drawer.AdminPanel;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.drawer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class MainAdminPanelActivity extends AppCompatActivity {
    Button add_notification;
    EditText notification_title;
    EditText notification_content;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Notifications");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin_panel);

        add_notification = findViewById(R.id.add_notification);
        notification_title = findViewById(R.id.notification_title);
        notification_content = findViewById(R.id.notification_content);

        add_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog dialog = new ProgressDialog(MainAdminPanelActivity.this);
                dialog.setMessage("sending the Notification");
                dialog.show();
                String title = notification_title.getText().toString();
                String content = notification_content.getText().toString();

                HashMap<String, String> map = new HashMap<>();
                map.put("title", title);
                map.put("content", content);

                reference.push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        dialog.dismiss();
                        Toast.makeText(MainAdminPanelActivity.this, "Notification Sent", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        dialog.dismiss();
                        Toast.makeText(MainAdminPanelActivity.this, "Could not Send the notification check you network and try again", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}