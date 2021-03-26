package com.example.mysqlproject;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SessionDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "usernameSessions";
    private static final String TABLE_SESSION="session_"+getCurrentDate();
    private static final String KEY_ID = "id";
    private static final String KEY_EXERCISE = "exercise";
    private static final String KEY_SET = "setnum";
    private static final String KEY_REPS = "reps";
    private static final String KEY_KG = "kg";

    public SessionDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SESSION_TABLE = "CREATE TABLE " + TABLE_SESSION + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_EXERCISE + " TEXT," + KEY_SET + " INTEGER," + KEY_REPS + " INTEGER," + KEY_KG + " DOUBLE" + ")";
        db.execSQL(CREATE_SESSION_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SESSION);

        // Create tables again
        onCreate(db);
    }

    // code to add the new excercise
    void addExercise(Exercise exercise) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EXERCISE, exercise.get_exercise()); // exercise
        values.put(KEY_SET, exercise.get_set()); // set
        values.put(KEY_REPS, exercise.get_reps()); // reps
        values.put(KEY_KG, exercise.get_kg()); // kg

        // Inserting Row
        db.insert(TABLE_SESSION, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single exercise line
    Exercise getExercise(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SESSION, new String[] { KEY_ID,
                        KEY_EXERCISE, KEY_SET, KEY_REPS, KEY_KG}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Exercise exercise = new Exercise(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)),Double.parseDouble(cursor.getString(4)));
        // return user
        return exercise;
    }



    // code to get all exercises in a list view
    public List<Exercise> getAllExercises() {
        List<Exercise> exerciseList = new ArrayList<Exercise>();

        String selectQuery = "SELECT  * FROM " + TABLE_SESSION;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Exercise exercise = new Exercise();
                exercise.set_id(Integer.parseInt(cursor.getString(0)));
                exercise.set_exercise(cursor.getString(1));
                exercise.set_set(Integer.parseInt(cursor.getString(2)));
                exercise.set_reps(Integer.parseInt(cursor.getString(3)));
                exercise.set_kg(Double.parseDouble(cursor.getString(4)));
                // Adding exercise to list
                exerciseList.add(exercise);
            } while (cursor.moveToNext());
        }

        // return excerice list
        return exerciseList;
    }

    //get all exercises from particular table
    public List<Exercise> getAllExercises(String tableName) {
        List<Exercise> exerciseList = new ArrayList<Exercise>();

        String selectQuery = "SELECT  * FROM " + tableName;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Exercise exercise = new Exercise();
                //exercise.set_id(Integer.parseInt(cursor.getString(0)));
                exercise.set_exercise(cursor.getString(1));
                exercise.set_set(Integer.parseInt(cursor.getString(2)));
                exercise.set_reps(Integer.parseInt(cursor.getString(3)));
                exercise.set_kg(Double.parseDouble(cursor.getString(4)));
                // Adding exercise to list
                exerciseList.add(exercise);
            } while (cursor.moveToNext());
        }

        // return excerice list
        return exerciseList;
    }




    // code to update the single exercise
    public int updateExercise(Exercise exercise) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EXERCISE, exercise.get_exercise()); // exercise
        values.put(KEY_SET, exercise.get_set()); // set
        values.put(KEY_REPS, exercise.get_reps()); // reps
        values.put(KEY_KG, exercise.get_kg()); // kg

        // updating row
        return db.update(TABLE_SESSION, values, KEY_ID + " = ?",
                new String[] { String.valueOf(exercise.get_id()) });
    }

    // Deleting single exercise line (one set)
    public void deleteExercise(Exercise exercise) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SESSION, KEY_ID + " = ?",
                new String[] { String.valueOf(exercise.get_id()) });
        db.close();
    }

    // Getting exercise Count
    public int getExerciseCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SESSION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // code to get all exercises in a list view
    public ArrayList<String> getAllTables() {
        ArrayList<String> tableList = new ArrayList<String>();
        // Select All Query
        //String selectQuery = "SELECT name FROM sqlite_master WHERE type = 'table'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type = 'table' AND name NOT LIKE 'android_%'", null);
        if (cursor.moveToFirst()) {
            while ( !cursor.isAfterLast() ) {
                tableList.add( cursor.getString( cursor.getColumnIndex("name")) );
                cursor.moveToNext();
            }
        }

        return tableList;
    }

    public static String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate now = LocalDate.now();
        //Log.d("Current date", dtf.format(now));
        return dtf.format(now);
    }

}