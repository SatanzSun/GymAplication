package com.example.mysqlproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements TextWatcher,
        CompoundButton.OnCheckedChangeListener {

    private EditText etUsername, etPass;
    private CheckBox rem_userpass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Button login, register;
    UserDatabaseHandler DBH;

    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DBH= new UserDatabaseHandler(this);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        etUsername = (EditText)findViewById(R.id.username);
        etPass = (EditText)findViewById(R.id.pass);
        rem_userpass = (CheckBox)findViewById(R.id.checkBox);

        if(sharedPreferences.getBoolean(KEY_REMEMBER,false))
            rem_userpass.setChecked(true);
        else
            rem_userpass.setChecked(false);

        etUsername.setText(sharedPreferences.getString(KEY_USERNAME, ""));
        etPass.setText(sharedPreferences.getString(KEY_PASS, ""));

        etUsername.addTextChangedListener(this);
        etPass.addTextChangedListener(this);
        rem_userpass.setOnCheckedChangeListener(this);
        login=findViewById(R.id.btnLogin);
        register=findViewById(R.id.btnRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(regIntent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameTXT = etUsername.getText().toString();
                String passwordTXT = etPass.getText().toString();

                User checkinsertdata = DBH.getUser(usernameTXT, passwordTXT);
                if(usernameTXT.isEmpty() || passwordTXT.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                }else {
                    if (checkinsertdata != null) {
                        Toast.makeText(LoginActivity.this, "Login Sucessfull", Toast.LENGTH_SHORT).show();
                        Intent homeIntent = new Intent(LoginActivity.this, HomeScreene.class);
                        startActivity(homeIntent);
                    } else
                        Toast.makeText(LoginActivity.this, "Incorect Password or Login", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { managePrefs(); }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) { managePrefs(); }

    private void managePrefs(){
        if(rem_userpass.isChecked()){
            editor.putString(KEY_USERNAME, etUsername.getText().toString().trim());
            editor.putString(KEY_PASS, etPass.getText().toString().trim());
            editor.putBoolean(KEY_REMEMBER, true);
            editor.apply();
        }else {
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_PASS);
            editor.remove(KEY_USERNAME);
            editor.apply();
        }
    }
}