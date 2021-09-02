
package com.example.drawer.studegroth;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drawer.R;
import com.github.mikephil.charting.charts.BarChart;

    public class StudentGroth extends AppCompatActivity {
        Button btnBarChart, btnPieChart;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_student_growth);


            btnBarChart = (Button) findViewById(R.id.btnBarChart);
            btnPieChart = (Button) findViewById(R.id.btnPieChart);
            btnBarChart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent I = new Intent(StudentGroth.this, bar_chart.class);
                    startActivity(I);
                }
            });
            btnPieChart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent I = new Intent(StudentGroth.this, PieChartActivty.class);
                    startActivity(I);
                }
            });
        }
    }