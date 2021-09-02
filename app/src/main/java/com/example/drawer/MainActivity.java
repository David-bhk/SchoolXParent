package com.example.drawer;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.example.drawer.CalendarStuff.School_Calendar;
import com.example.drawer.Not.Notification;
import com.example.drawer.studegroth.StudentGroth;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FloatingActionButton floatingActionButton;
    CardView notification;
    CardView home;
    CardView chat;
    CardView timetable;
    CardView calendar;
    CardView studGroth;
    //    FrameLayout frameLayout;
    TextView textView11, textView12;
    String id;
    CircleImageView profile;
    private FirebaseAuth auth;
//    ImageView prof;
//    private Uri imageUri;
//    private FirebaseStorage storage;
//    private StorageReference storageReference;

//HERE WE WANT TO SPECIALIZE WHICH ACCOUNT TO STATRT
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            // User is signed in
        } else {
            Intent intToHome = new Intent(this, LoginActivity.class);
            startActivity(intToHome);
            finish();
            // No user is signed in
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = findViewById(R.id.fab);

        auth = FirebaseAuth.getInstance();

        /*Intent intent = new Intent(this, StudentList.class);
        startActivity(intent);*/

//        prof = (ImageView) findViewById(R.id.profile);
//        storage = FirebaseStorage.getInstance();
//        storageReference = storage.getReference();
//
//        prof.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_SCREEN_ON, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////                intent.setType("image/*");
//                startActivityForResult(intent,1);
//            }
//        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        textView11 = (TextView) headerView.findViewById(R.id.emailHeader);
        textView12 = (TextView) headerView.findViewById(R.id.nameHeader);
        profile = headerView.findViewById(R.id.profile);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar1);
//        frameLayout  =findViewById(R.id.frame_container);
        notification = (CardView) findViewById(R.id.cardview1);
        calendar = findViewById(R.id.cardview4);
        timetable = findViewById(R.id.cardView6);
        studGroth = findViewById(R.id.cardView2);
        home = findViewById(R.id.cardview5);
        chat = (CardView) findViewById(R.id.cardView3);

        /*Intent w = getIntent();
        id = w.getStringExtra("id");
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();*/

        // SETTING ONCLICK LISTENER TO THE FLOATING BUTTON

        floatingActionButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startSupportChat ();
            }
        } );




        setSupportActionBar(toolbar);
        getUserInfo();
        notification.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Notification.class)));

        home.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, com.example.drawer.Result.ResultGrid.class)));

        calendar.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, School_Calendar.class)));

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.drawer.chatf.MainActivity.class);
                intent.putExtra("userId", auth.getUid());
                startActivity(intent);
                finish();
            }
        });
        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, com.example.drawer.timetable.timetable.class));
            }
        });
        studGroth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StudentGroth.class));

            }
        });

        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


    }
    //impletemeting whatsAppinthe app
    private void startSupportChat() {

        try {
            String headerReceiver = "Hello! is there someone???";// Replace with your message.
            String bodyMessageFormal = "I have an issue with the App so i wanted you guys to help me.";// Replace with your message.
            String whatsappContain = headerReceiver + bodyMessageFormal;
            String trimToNumner = "+256754888626"; //10 digit number
            Intent intent = new Intent ( Intent.ACTION_VIEW );
            intent.setData ( Uri.parse ( "https://wa.me/" + trimToNumner + whatsappContain ) );
            startActivity ( intent );
        } catch (Exception e) {
            e.printStackTrace ();
        }

    }

    private void getUserInfo() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Accounts").child(auth.getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                textView11.setText(snapshot.child("email").getValue().toString());
                textView12.setText(snapshot.child("username").getValue().toString());
                try {
                    Glide.with(getApplicationContext())
                            .load(snapshot.child("profile").getValue().toString())
                            .centerCrop()
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .into(profile);

                } catch (Exception e) {
//                    Toast.makeText(MainActivity.this, "" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

       /* DocumentReference reference=FirebaseFirestore.getInstance().collection("Users").document(auth.getUid());

        reference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent( DocumentSnapshot value, FirebaseFirestoreException error) {
               textView11.setText(value.get("email").toString());
               textView12.setText(value.get("username").toString());
               try {
                   Glide.with(getApplicationContext()).load(value.get("profile"));
               }catch (Exception e){
                   Toast.makeText(MainActivity.this, ""+e.getMessage().toString(), Toast.LENGTH_SHORT).show();
               }

            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.DeletePassword) {
            startActivity(new Intent(MainActivity.this, com.example.drawer.userProfile.DeleteAcount.class));


        } else if (id == R.id.ChangeProfile) {
            startActivity(new Intent(MainActivity.this, com.example.drawer.userProfile.ChangePassword.class));

        } else if (id == R.id.LogOut) {
            Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();

        }
        return true;
    }

    private void logout() {
        auth.signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    //onclick listener on the navigation bar
    public boolean onNavigationItemSelected(@NonNull MenuItem item1) {
        int id = item1.getItemId();
        if (id == R.id.nav_not) {
            Intent newIntent = new Intent(this, Notification.class);
            startActivity(newIntent);

        } else if (id == R.id.nav_result) {
            Intent newIntent = new Intent(this, com.example.drawer.chatf.MainActivity.class);
            startActivity(newIntent);

        } else if (id == R.id.LogOut) {
            logout();
        }
        return true;

    }

    private void CreteDailog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are You Sure You Want To LogOut?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intSignUp = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intSignUp);

            }
        });
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
////        resultCode == RESULT_OK && data!=null && data.getData()!=null){
//        if (requestCode ==1){
//            if (resultCode == RESULT_OK){
//                imageUri = data.getData();
//                prof.setImageURI(imageUri);
//            }
//        }
//    }
}