package com.example.drawer.lecturers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.drawer.R;
import com.example.drawer.Result.Result;
import com.example.drawer.Result.ResultAdapter;
import com.example.drawer.Result.ResultModel;
import com.example.drawer.timetable.MyAdapter;
import com.example.drawer.timetable.User;
import com.example.drawer.timetable.mtwtfs;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class lec extends AppCompatActivity {
    FirebaseAuth auth;
    private String myId;
    private RecyclerView recyclerView;
    private DatabaseReference database;;
    private ArrayList<LecModel> list;
    private com.example.drawer.lecturers.lecAdapter lecAdapter;
    private ProgressBar progressBar;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lec);
        recyclerView = findViewById(R.id.userList);
        progressBar = findViewById(R.id.go);
        // get student Id
        SharedPreferences sharedPref = getSharedPreferences(null, Context.MODE_PRIVATE);
        id = sharedPref.getString("id", null);

        recyclerView = findViewById(R.id.MyRecycle);
        progressBar = findViewById(R.id.go);
        progressBar.setVisibility(View.VISIBLE);

        auth = FirebaseAuth.getInstance();
        myId = auth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance().getReference("lectures").child(id).child("maths");
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        recyclerView.setAdapter(lecAdapter);

        lecAdapter = new lecAdapter(this,list);
//        recyclerView.setAdapter(lecAdapter);
        list.clear();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        progressBar.setVisibility(View.GONE);
                        LecModel user1 = dataSnapshot.getValue(LecModel.class);
                        list.add(user1);
                    }
                    lecAdapter.notifyDataSetChanged();
                }
                else {
//                    Toast.makeText( "No data ....", Toast.LENGTH_SHORT).show();
                    Toast.makeText(lec.this, "No Lectures available ...", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);

                }
                }



            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}