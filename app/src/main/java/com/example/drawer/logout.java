package com.example.drawer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class logout extends AppCompatActivity {

    private Button logout;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        logout = findViewById(R.id.logout_button);

        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
       /* progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);*/

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

    }


    private void logout() {
//        progressDialog.show();
        auth.signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();

      /*  FirebaseAuth.AuthStateListener listener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                progressDialog.dismiss();
                if(firebaseAuth.getCurrentUser() == null){
                    //logout successful
                    AlertDialog.Builder builder = new AlertDialog.Builder(logout.this, R.style.DialogStyle);
                    builder.setMessage("Logout successful !!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    builder.show();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(logout.this, R.style.DialogStyle);
                    builder.setMessage("Oops..please try again !!");
                    builder.setPositiveButton("OK", null);
                    builder.show();
                }
            }
        };
        auth.addAuthStateListener(listener);*/
    }
}