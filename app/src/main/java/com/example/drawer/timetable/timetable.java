package com.example.drawer.timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.drawer.R;
import com.example.drawer.Result.ResultGrid;

public class timetable extends AppCompatActivity {
    CardView Monday;
    CardView Tuesday;
    CardView Wednesday;
    CardView Thursday;
    CardView Friday;
    CardView Saturday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        Monday = findViewById(R.id.cardView1);
        Tuesday = findViewById(R.id.cardView2);
        Wednesday = findViewById(R.id.cardView3);
        Thursday = findViewById(R.id.cardView4);
        Friday = findViewById(R.id.cardView5);
        Saturday = findViewById(R.id.cardView6);


        Monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(timetable.this, mtwtfs.class).putExtra("timetable", "Mo"));

            }
        });
        Tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(timetable.this, mtwtfs.class).putExtra("timetable", "Tu"));

            }
        });
        Wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(timetable.this, mtwtfs.class).putExtra("timetable", "We"));

            }
        });
        Thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(timetable.this, mtwtfs.class).putExtra("timetable", "Th"));

            }
        });
        Friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(timetable.this, mtwtfs.class).putExtra("timetable", "Fr"));

            }
        });
        Saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(timetable.this, mtwtfs.class).putExtra("timetable", "Sa"));

            }
        });




    }
}