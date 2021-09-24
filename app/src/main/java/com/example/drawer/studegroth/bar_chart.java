package com.example.drawer.studegroth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class bar_chart extends AppCompatActivity {
    DatabaseReference database;
    private String id;
    private BarDataSet dataset;
    private BarData barData;
    private ArrayList<BarEntry> information;
    private BarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        chart = findViewById(R.id.barchart);

        information = new ArrayList<>();
        dataset = new BarDataSet(information, "Average");


        // get student Id
        SharedPreferences sharedPref = getSharedPreferences(null, Context.MODE_PRIVATE);
        id = sharedPref.getString("id", null);

        getTerms(1);
        getTerms(2);
        getTerms(3);
        getTerms(4);
        getTerms(5);
        getTerms(6);

        information.add(new BarEntry(1, 0));
        information.add(new BarEntry(2, 0));
        information.add(new BarEntry(3, 0));
        information.add(new BarEntry(4, 0));
        information.add(new BarEntry(5, 0));
        information.add(new BarEntry(6, 0));

        dataset.setValueTextColor(Color.BLACK);
        dataset.setValueTextSize(15f);

        barData = new BarData(dataset);

        chart.setFitBars(true);
        chart.setData(barData);
        chart.getDescription().setText("Student's Bar Report");
        chart.animateY(5000);
    }

    private void getTerms(int i) {
        database = FirebaseDatabase.getInstance()
                .getReference("Results")
                .child(id)
                .child("Term" + i);

        database.addValueEventListener(new ValueEventListener() {
            private int count;
            private int total;

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    ResultModel resultModel = snapshot1.getValue(ResultModel.class);


                    String marks = resultModel.getMarks();
                    int marks1 = Integer.parseInt(marks);
                    total += marks1;
                    count++;
                }
                int avg = total / count;

                information.set(i - 1, new BarEntry(i, avg));
                dataset.notifyDataSetChanged();
                barData.notifyDataChanged();
                chart.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}


