package com.example.mysqlproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RunningExcercise extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layoutList;
    Button buttonAdd;
    private int setCounter=0;
    private String setnum;
    public static List <String> savedReps = new ArrayList<>();
    public static List <String> savedKg = new ArrayList<>();
    private String exerciseTitleString;
    SessionDatabaseHandler session_db = new SessionDatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_excercise);
        layoutList=findViewById(R.id.layout_list);
        buttonAdd=findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(this);
        Intent intent = getIntent();
        TextView exerciseTitle = findViewById(R.id.exercise_title);
        exerciseTitle.setText(intent.getStringExtra("exercise_title"));
        exerciseTitleString=intent.getStringExtra("exercise_title");
    }
    @Override
    public void onClick(View v) {
        if (setCounter>0) {
            EditText editReps = layoutList.findViewWithTag("edit_reps" + String.valueOf(setCounter));
            EditText editKg = layoutList.findViewWithTag("edit_kg" + String.valueOf(setCounter));
            if (editReps.getText().toString().equals("") || editKg.getText().toString().equals("")){
                Toast toast = Toast.makeText(getApplicationContext(),"Please, provide input for both reps and kg!",Toast.LENGTH_SHORT);
                toast.show();
            } else {
                savedReps.add(editReps.getText().toString());
                savedKg.add(editKg.getText().toString());
                addView();
            }
        } else
            addView();
    }

    public void addView() {
        setCounter++;
        View setView = getLayoutInflater().inflate(R.layout.row_add_set, null, false);
        setnum = "Set " + String.valueOf(setCounter);
        TextView setNum = setView.findViewById(R.id.edit_setnum);
        setNum.setText(setnum);
        EditText editReps = (EditText)setView.findViewById(R.id.edit_reps);
        editReps.setTag("edit_reps" + String.valueOf(setCounter));
        EditText editKg = (EditText)setView.findViewById(R.id.edit_kg);
        editKg.setTag("edit_kg" + String.valueOf(setCounter));

        ImageView imageClose = (ImageView)setView.findViewById(R.id.image_remove);
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(setView);
            }
        });
        layoutList.addView(setView);
    }
    private void removeView (View view) {
        setCounter--;
        layoutList.removeView(view);
    }

    public void saveExerciseAndReturn(View view){

        EditText editReps = layoutList.findViewWithTag("edit_reps" + String.valueOf(setCounter));
        EditText editKg = layoutList.findViewWithTag("edit_kg" + String.valueOf(setCounter));
        if (editReps.getText().toString().equals("") || editKg.getText().toString().equals("")){
            Toast toast = Toast.makeText(getApplicationContext(),"Please, check last set inputs",Toast.LENGTH_SHORT);
            toast.show();
        } else {
            if (setCounter > 0) {
                savedReps.add(editReps.getText().toString());
                savedKg.add(editKg.getText().toString());
                for (int i = 0; i <= setCounter - 1; i++) {
                    String reps = savedReps.get(i);
                    session_db.addExercise(new Exercise(exerciseTitleString, i + 1,
                            Integer.parseInt(savedReps.get(i)), Double.parseDouble(savedKg.get(i))));
                }
            }
            Intent intent = new Intent(getApplicationContext(), AddExerciseOrEndSession.class);
            startActivity(intent);
            Toast toast = Toast.makeText(getApplicationContext(),"Your exercise has bee saved!",Toast.LENGTH_SHORT);
            toast.show();
            Log.d("Reading Exercise: ", "Reading all exercises..");
            List<Exercise> exercises = session_db.getAllExercises();

            for (Exercise exer : exercises) {
                String log = "Id: " + exer.get_id() + " ,Exercise: " + exer.get_exercise() + " ,Set: " +
                        exer.get_set() + " , Reps: " + exer.get_reps() + " ,KG: " + exer.get_kg();
                // Writing Exercise to log
                Log.d("Exercise: ", log);
            }
        }


    }

}