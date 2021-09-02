package com.example.drawer.userProfile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.drawer.MainActivity;
import com.example.drawer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class ChangePassword extends AppCompatActivity {
    EditText editText1, editText2;
   FirebaseAuth firebaseAuth;
    ProgressDialog dialog;
    Button change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        editText1 = findViewById(R.id.password1);
//        editText2 = findViewById(R.id.password2);
        change = findViewById(R.id.changeP);
        dialog =new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null){
                    dialog.setMessage("Changing Password, Please Wait");
                    dialog.show();
                    user.updatePassword(editText1.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(ChangePassword.this, "Your Password Has Benn Changed Successfully", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                        firebaseAuth.signOut();
                                        finish();
                                        Intent i = new Intent(ChangePassword.this, MainActivity.class);
                                    }
                                    else {
                                        dialog.dismiss();
                                        Toast.makeText(ChangePassword.this, "Your Password Couldn't Be Changed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


//            user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(Task<Void> task) {
//                    if (task.isSuccessful()){
//                        Toast.makeText(ChangePassword.this, "Account Deactivated", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });

                }

            }
        });





    }
}