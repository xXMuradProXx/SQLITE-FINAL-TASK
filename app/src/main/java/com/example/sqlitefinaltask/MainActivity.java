package com.example.sqlitefinaltask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        Utils.createAllTables(db);
        Utils.addDefaultStudents(db);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int selectedId = item.getItemId();
        Intent intent = new Intent();

        if(selectedId == R.id.all_students){
            intent = new Intent(MainActivity.this, AllStudents_Screen.class);
        }
        if(selectedId == R.id.add_students){
            intent = new Intent(MainActivity.this, AddStudents_Screen.class);
        }
        if(selectedId == R.id.details){
            intent = new Intent(MainActivity.this, Details_Screen.class);
        }
        startActivity(intent);
        return true;
    }

}