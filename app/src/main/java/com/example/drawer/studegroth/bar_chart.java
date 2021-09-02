package com.example.drawer.studegroth;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawer.R;
import com.example.drawer.Result.Result;
import com.example.drawer.Result.ResultAdapter;
import com.example.drawer.Result.ResultModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class bar_chart extends AppCompatActivity {
     DatabaseReference database;
     //ProgressBar progressBar;
     //ArrayList<ResultModel> list;
     //com.example.drawer.Result.ResultAdapter resultAdapter;
     //RecyclerView recyclerView;
     private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        BarChart chart = findViewById(R.id.barchart);


//        SharedPreferences sharedPref = getSharedPreferences(null, Context.MODE_PRIVATE);
//
////        int term2 = sharedPref.getInt("Term2", 0);
//
//        int term1 = sharedPref.getInt("Term1", 0);
//        int term2 = sharedPref.getInt("Term2", 0);

//        ArrayList NoOfEmp = new ArrayList();
//
//        NoOfEmp.add(new BarEntry(90, 0));
//        NoOfEmp.add(new BarEntry(78, 1));
//        NoOfEmp.add(new BarEntry(30, 2));
//        NoOfEmp.add(new BarEntry(40, 3));
//        NoOfEmp.add(new BarEntry(50, 4));
//        NoOfEmp.add(new BarEntry(60, 5));


//        ArrayList year = new ArrayList();
//
//        year.add("Term1");
//        year.add("Term2");
//        year.add("Sem1");
//        year.add("Term3");
//        year.add("Term4");
//        year.add("Sem2");

        Intent w = getIntent();
        String term = w.getStringExtra("term");


        // get student Id
        SharedPreferences sharedPref = getSharedPreferences(null, Context.MODE_PRIVATE);
        id = sharedPref.getString("id", null);

        database = FirebaseDatabase.getInstance().getReference("Results").child(id); //get all the items  soit in incrementing or in a list or loop

        ResultModel resultModel1 = new ResultModel("Subjects", "C.U.", "Marks", "id");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    //progressBar.setVisibility(View.GONE);
                    int total = 0;
                    int count = 0;
                    int GPA = 0;
                    //list.clear();
                    List<BarEntry> barEntries = new ArrayList<>();

                    String Term1 =dataSnapshot.child("Term1").child("jd").child("marks").getValue().toString();
                    String Term12 =dataSnapshot.child("Term1").child("ji").child("marks").getValue().toString();
                    String Term2 =dataSnapshot.child("Term2").child("jii").child("marks").getValue().toString();
                    String Term3 =dataSnapshot.child("Term3").child("jiii").child("marks").getValue().toString();
                    String Term4 =dataSnapshot.child("Term4").child("jiii").child("marks").getValue().toString();
                    String Term5 =dataSnapshot.child("Term5").child("jiii").child("marks").getValue().toString();
                    String Term6 =dataSnapshot.child("Term6").child("jiiii").child("marks").getValue().toString();
                    Float moyenne=Float.parseFloat(Term1)+Float.parseFloat(Term12);

                    barEntries.add(new BarEntry(Integer.parseInt("11"), moyenne/2));
                    barEntries.add(new BarEntry(Integer.parseInt("21"), Integer.parseInt(Term2)));
                    barEntries.add(new BarEntry(Integer.parseInt("31"), Integer.parseInt(Term3)));
                    barEntries.add(new BarEntry(Integer.parseInt("41"), Integer.parseInt(Term4)));
                    barEntries.add(new BarEntry(Integer.parseInt("51"), Integer.parseInt(Term5)));
                    barEntries.add(new BarEntry(Integer.parseInt("61"), Integer.parseInt(Term6)));


//                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                        ResultModel resultModel = dataSnapshot1.getValue(ResultModel.class);
//                       List<String> marks=new ArrayList<>();
//                        if (resultModel.getMarks()!=null){
//                            marks.add(resultModel.getMarks());
//                            barEntries.add(new BarEntry(Integer.parseInt("11"), Integer.parseInt(marks.get(0))));
//                        }


                       // list.add(resultModel);

                        //String marks = resultModel1.getMarks();
//                        resultModel1.setMarks(resultModel.getMarks());
//                        resultModel1.setId(resultModel.getId());
//
//                        barEntries.add(new BarEntry(Integer.parseInt(resultModel1.getMarks()), Integer.parseInt(resultModel1.getId())));
//                        barEntries.add(new BarEntry(Integer.parseInt("11"), Integer.parseInt("70")));
//                        barEntries.add(new BarEntry(Integer.parseInt("21"), Integer.parseInt("89")));
//                        //barEntries.add(new BarEntry(Integer.parseInt(resultModel1.getMarks()), Integer.parseInt(resultModel1.getId())));


//                        int marks1 = Integer.parseInt(marks);
//                        total += marks1;
//                        count++;
                 //   }

                    BarDataSet bardataset = new BarDataSet(barEntries, "Student Average");
                    chart.animateY(2000);
                    BarData data = new BarData(bardataset);
                    chart.setData(data);


                    //resultAdapter = new ResultAdapter(bar_chart.this, list);
                    //recyclerView.setAdapter(resultAdapter);

                    //resultAdapter.notifyDataSetChanged();

//                    int avg = total / count;
//
//                    TextView etTotal = findViewById(R.id.etTotal);
//                    TextView etAvg = findViewById(R.id.etAvg);
//                    TextView etGPA = findViewById(R.id.etGPA);
//
//                    etTotal.setText("Total: " + total);
//                    etAvg.setText("Average: " + avg + "%");
////                etGPA.setText("Average: " + avg);
//                    if (avg >= 50 && avg <= 65) {
//                        etGPA.setText("GPA: " + " b");
//                    } else if (avg >= 80 && avg <= 88) {
//                        etGPA.setText("GPA: " + " A");
//
//                    } else {
//                        Toast.makeText(bar_chart.this, "unaChindwa", Toast.LENGTH_SHORT).show();
//
//                    }

//                    SharedPreferences sharedPref = getSharedPreferences(null, Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPref.edit();
//                    editor.putInt("Term1", avg);
//                    editor.putInt("Term2", avg);
//
//                    editor.apply();

                } else {
                    Toast.makeText(bar_chart.this, "No Data!", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
