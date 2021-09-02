package com.example.drawer.timetable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.drawer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class mtwtfs extends AppCompatActivity {
//    private RecyclerView recyclerView;
//    private DatabaseReference database;
//    private com.example.drawer.timetable.MyAdapter myAdapter;
//    private ArrayList<User> list;
//    private Context mContext;
//    private String id;
    FirebaseAuth auth;
    ProgressBar progressBar;
    String myId;
    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<User> list;
    private String id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtwtfs);
//        SharedPreferences sharedPref = mContext.getSharedPreferences(null, Context.MODE_PRIVATE);
//        id = sharedPref.getString("id", null);


//        recyclerView = findViewById(R.id.userList);
////        ProgressBar progressBar = findViewById(R.id.go);
////        progressBar.setVisibility(View.VISIBLE);
//        database = FirebaseDatabase.getInstance().getReference("Users");
////        database = FirebaseDatabase.getInstance().getReference("Timetable").child(id).child("LUNDI");
//
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        list = new ArrayList<>();
//        myAdapter = new MyAdapter(getContext(),list);
//        recyclerView.setAdapter(myAdapter);
//        // Inflate the layout for this fragment

        // get selected grid
        Intent w = getIntent();
        String timetable = w.getStringExtra("timetable");

        // get student Id
        SharedPreferences sharedPref = getSharedPreferences(null, Context.MODE_PRIVATE);
        id = sharedPref.getString("id", null);

        recyclerView = findViewById(R.id.userList);
        progressBar = findViewById(R.id.go);
        progressBar.setVisibility(View.VISIBLE);
        auth = FirebaseAuth.getInstance();
        myId = auth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance().getReference("Us").child(id).child("timetable" + timetable);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        progressBar.setVisibility(View.GONE);
                        User user1 = dataSnapshot.getValue(User.class);
                        list.add(user1);
                    }

                    myAdapter.notifyDataSetChanged();

                }
                else {
//                    Toast.makeText( "No data ....", Toast.LENGTH_SHORT).show();
                    Toast.makeText(mtwtfs.this, "No data ....", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
