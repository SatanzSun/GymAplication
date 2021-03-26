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
import java.util.List;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        SessionDatabaseHandler session_db = new SessionDatabaseHandler(this);

        ListView sessionList = findViewById(R.id.sessionListByDate);
        ArrayList <String> sessions = session_db.getAllTables();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,sessions);

        sessionList.setAdapter(arrayAdapter);

        sessionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String session = sessions.get(position);
                Intent intent = new Intent(getApplicationContext(),SingleSessionLog.class);
                intent.putExtra("table_name",session);
                startActivity(intent);


            }
        });

        Log.d("Table names ",session_db.getAllTables().toString());

    }
}