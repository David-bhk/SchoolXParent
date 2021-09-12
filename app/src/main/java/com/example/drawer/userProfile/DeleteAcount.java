package com.example.drawer.userProfile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.LoginActivity;
import com.example.drawer.MainActivity;
import com.example.drawer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class DeleteAcount extends AppCompatActivity {
    Button delete;
//  ProgressDialog dialog;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_acount);

        delete = findViewById(R.id.delete_button);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getInstance().getCurrentUser();

//        delete.setText(firebaseUser.getEmail());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder passwordDialog = new AlertDialog.Builder(v.getContext());
                passwordDialog.setTitle("Delete Account");
                passwordDialog.setMessage("If You Delete Your Account you will not be able to login");

                passwordDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(DeleteAcount.this, "Your Account Has Benn Deleted Successfully", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(DeleteAcount.this, LoginActivity.class);
                                    finish();

                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                                }else {
                                    Toast.makeText(DeleteAcount.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                }

                            }
                        });

                    }
                });
                passwordDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();

                    }
                });
                AlertDialog alertDialog = passwordDialog.create();
                alertDialog.show();
            }
        });
    }

}