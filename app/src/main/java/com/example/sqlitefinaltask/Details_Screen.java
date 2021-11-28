package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Details_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_screen);

        getSupportActionBar().hide();

    }
}