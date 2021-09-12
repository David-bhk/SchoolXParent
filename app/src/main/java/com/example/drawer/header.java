package com.example.drawer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

public class header extends AppCompatActivity {
    TextView email;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String UserID;
    private ImageView prof;
    private Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header);

  email = findViewById(R.id.emailHeader);

        prof = findViewById(R.id.profile);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SCREEN_ON, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intent.setType("image/*");
                startActivityForResult(intent,1);


            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        UserID = firebaseAuth.getCurrentUser().getUid();

        DocumentReference documentReference = firebaseFirestore.collection("Users").document(UserID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                email.setText(documentSnapshot.getString("email"));
                finish();
            }
        });
    }
//
//    private void ChoosePicture(){
//        Intent intent = new Intent(Intent.ACTION_SCREEN_ON, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////                intent.setType("image/*");
//        startActivityForResult(intent,1);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        resultCode == RESULT_OK && data!=null && data.getData()!=null){
        if (requestCode ==1){
            if (resultCode == RESULT_OK){
            imageUri = data.getData();
            prof.setImageURI(imageUri);
        }
        }
    }


//    private void uploadPicture() {
//        final String randomKey = UUID.randomUUID().toString();
//        StorageReference mountainsRef = storageReference.child("images/" +randomKey);
//        storageReference.putFile(imageUri);
//
//    }
}
