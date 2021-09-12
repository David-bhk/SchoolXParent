package com.example.drawer.Result;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Result extends AppCompatActivity {
    FirebaseAuth auth;
    String myId;
    private RecyclerView recyclerView;
    private DatabaseReference database;
    private com.example.drawer.Result.ResultAdapter resultAdapter;
    private ArrayList<ResultModel> list;
    private ProgressBar progressBar;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // get selected term
        Intent w = getIntent();
        String term = w.getStringExtra("term");


        // get student Id
        SharedPreferences sharedPref = getSharedPreferences(null, Context.MODE_PRIVATE);
        id = sharedPref.getString("id", null);

        recyclerView = findViewById(R.id.MyRecycle);
        progressBar = findViewById(R.id.go);
        progressBar.setVisibility(View.VISIBLE);
        //here we're creating a child called Result, id uder Results, and Term++
        database = FirebaseDatabase.getInstance().getReference("Results").child(id).child("Term" + term);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        auth = FirebaseAuth.getInstance();
        myId = auth.getCurrentUser().getUid();
        list = new ArrayList<>();
        //setting our textview for the result activity
        ResultModel resultModel1 = new ResultModel("Subjects", "C.U.", "Marks", "id");


        list.clear();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    progressBar.setVisibility(View.GONE);
                    //declaration of variable that will help us to calculate the GPA ANd total of marks
                    int total = 0;
                    int count = 0;
                    int GPA = 0;
                    list.clear();

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        ResultModel resultModel = dataSnapshot1.getValue(ResultModel.class);

                        list.add(resultModel1);
                        list.add(resultModel);


                        String marks = resultModel.getMarks();
                        int marks1 = Integer.parseInt(marks);
                        total += marks1;
                        count++;
                    }
                    resultAdapter = new ResultAdapter(Result.this, list);
                    recyclerView.setAdapter(resultAdapter);

                    resultAdapter.notifyDataSetChanged();

                    int avg = total / count;
//textview for etTotal, etAvg, etGPA
                    TextView etTotal = findViewById(R.id.etTotal);
                    TextView etAvg = findViewById(R.id.etAvg);
                    TextView etGPA = findViewById(R.id.etGPA);

                    etTotal.setText("Total: " + total);
                    etAvg.setText("Average: " + avg + "%");
//                etGPA.setText("Average: " + avg);
                    //setting condition for the etGPA
                    if (avg >= 50 && avg <= 65) {
                        etGPA.setText("GPA: " + " b");
                    } else if (avg >= 66 && avg <= 75) {
                        etGPA.setText("GPA: " + " B+");
                    } else if (avg >= 80 && avg <= 88) {
                        etGPA.setText("GPA: " + "A");

                    } else if (avg >= 89 && avg <= 95) {
                        etGPA.setText("GPA: " + "A+");

                    } else {
                        etGPA.setText("GPA: " + " D");
                        Toast.makeText(Result.this, "You Have Failed", Toast.LENGTH_SHORT).show();

                    }

                    SharedPreferences sharedPref1 = getSharedPreferences(null, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = sharedPref1.edit();
                    editor1.putInt("Term1", avg);
                    editor1.putInt("term2", avg);
                    editor1.putInt("term3", avg);
                    editor1.putInt("term4", avg);
                    editor1.putInt("term5", avg);
                    editor1.putInt("term6", avg);

                    editor1.apply();

                } else {
                    //progress bar visibility GONE
                    Toast.makeText(Result.this, "No Data!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}