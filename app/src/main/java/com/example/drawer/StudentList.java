package com.example.drawer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class StudentList extends AppCompatActivity {

    private RecyclerView recView;
    private FirebaseUser user;
    private FirebaseRecyclerAdapter<Student, studentViewholder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        user = FirebaseAuth.getInstance().getCurrentUser();

        recView = findViewById(R.id.recyclerView);
        recView.setLayoutManager(new LinearLayoutManager(this));

        getData();

    }

    private void getData() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Accounts")
                .child(user.getUid())
                .child("Children");

        FirebaseRecyclerOptions<Student> options
                = new FirebaseRecyclerOptions.Builder<Student>()
                .setQuery(ref, Student.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Student, studentViewholder>(options) {
            @NonNull
            @NotNull
            @Override
            public studentViewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.student_item, parent, false);
                return new studentViewholder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull @NotNull StudentList.studentViewholder holder, int position, @NonNull @NotNull Student model) {
                // get database item id
                String id = getRef(position).getKey();

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Students").child(id);
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        String username = snapshot.child("username").getValue().toString();
                        String regno = snapshot.child("regno").getValue().toString();
                        holder.username.setText(String.format("%s - %s", username, regno));

                        holder.itemView.setOnClickListener(v -> {
                            String id = getRef(position).getKey();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);

                            // SAVE SELECTED STUDENT ID
                            SharedPreferences sharedPref = getSharedPreferences(null, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("id", id);
                            editor.apply();
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });
            }
        };
        recView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    class studentViewholder extends RecyclerView.ViewHolder {
        TextView username;

        public studentViewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            username = (itemView).findViewById(R.id.username);
        }
    }
}