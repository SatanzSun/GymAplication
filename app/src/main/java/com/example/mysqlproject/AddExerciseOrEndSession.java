package com.example.mysqlproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddExerciseOrEndSession extends AppCompatActivity {
    String currentDate;
    Button saveSession;
    public void addExercise(View view) {
        Intent intent = new Intent(getApplicationContext(), ExerciseList.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exerc_or_end_session);
        saveSession = findViewById(R.id.saveSession);
        saveSession.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AddExerciseOrEndSession.this, HomeScreene.class);
            startActivity(intent);

        }
    });




        TextView currDate = findViewById(R.id.currentDate);
        currDate.setText(getCurrentDate()); //date validation needed later
        currentDate=getCurrentDate();

    }


    public String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate now = LocalDate.now();
        //Log.d("Current date", dtf.format(now));
        return dtf.format(now);
    }
}