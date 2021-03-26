package com.example.mysqlproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ExerciseList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);

        ListView exerciseList = findViewById(R.id.exerciseList);

        ArrayList<String> exercises = new ArrayList<String>();

        exercises.add("Barbell Curl");
        exercises.add("Bench Press");
        exercises.add("Bent-Over-Row");
        exercises.add("Cable Curl");
        exercises.add("Cable fly");
        exercises.add("Cable Triceps Extension");
        exercises.add("Cable Triceps Kickback");
        exercises.add("Chest Dips");
        exercises.add("Close-Grip Pushup");
        exercises.add("Deadlift");
        exercises.add("Dumbbell Lunge");
        exercises.add("Front Raise");
        exercises.add("Front Squat");
        exercises.add("Hammer Curl");
        exercises.add("Incline Bench Press");
        exercises.add("Lat Pulldown");
        exercises.add("Leg Press");
        exercises.add("Pull-Up");
        exercises.add("Push press");
        exercises.add("Seated Shoulder Press");
        exercises.add("Squat");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,exercises);

        exerciseList.setAdapter(arrayAdapter);

        exerciseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String exercise = exercises.get(position);
                Intent intent = new Intent(getApplicationContext(),RunningExcercise.class);
                intent.putExtra("exercise_title",exercise);
                startActivity(intent);
            }
        });

    }
}