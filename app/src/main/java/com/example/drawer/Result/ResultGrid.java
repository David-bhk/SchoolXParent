package com.example.drawer.Result;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import com.example.drawer.R;

public class ResultGrid extends AppCompatActivity {

    AppCompatButton button1, button2, button3, button4, button5, button6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_grid);
        button1 = findViewById(R.id.oneperiod);
        button2 = findViewById(R.id.twoperiod);
        button3 = findViewById(R.id.Fisrtsem);
        button4 = findViewById(R.id.Threeperiod);
        button5 = findViewById(R.id.Fourperiod);
        button6 = findViewById(R.id.ndsem);
        //setting onclick listener on all the periods

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultGrid.this, com.example.drawer.Result.Result.class).putExtra("term", "1"));

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultGrid.this, com.example.drawer.Result.Result.class).putExtra("term", "2"));

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultGrid.this, com.example.drawer.Result.Result.class).putExtra("term", "3"));

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultGrid.this, com.example.drawer.Result.Result.class).putExtra("term", "4"));

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultGrid.this, com.example.drawer.Result.Result.class).putExtra("term", "5"));

            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultGrid.this, com.example.drawer.Result.Result.class).putExtra("term", "6"));

            }
        });

    }

}