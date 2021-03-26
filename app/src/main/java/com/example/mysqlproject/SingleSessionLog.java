package com.example.mysqlproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SingleSessionLog extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_session_log);
        SessionDatabaseHandler session_db = new SessionDatabaseHandler(this);
        Intent intent = getIntent();
        ListView singleSession = findViewById(R.id.singleSession);

        List<Exercise> exercises = session_db.getAllExercises(intent.getStringExtra("table_name"));
        ArrayList<String> allExercises = new ArrayList<>();
        for (Exercise exer : exercises) {
            String log = "Exercise: " + exer.get_exercise() + " ,Set: " +
                    exer.get_set() + " , Reps: " + exer.get_reps() + " ,KG: " + exer.get_kg();
            // Writing Exercise to log
            allExercises.add(log);
            Log.d("Exercise: ", log);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allExercises);
        singleSession.setAdapter(arrayAdapter);
    }
}