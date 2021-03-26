package com.example.mysqlproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText username, password, confirmPassword;
    Button register;

    UserDatabaseHandler user_db;

    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.pass);
        confirmPassword = findViewById(R.id.confirmPassword);
        register = findViewById(R.id.btnRegister);
        user_db = new UserDatabaseHandler(this);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameTXT = username.getText().toString();
                String passwordTXT = password.getText().toString();
                String confirmpasswordTXT = confirmPassword.getText().toString();

                if(TextUtils.isEmpty(usernameTXT)){
                    Toast.makeText(RegisterActivity.this, "Username must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(passwordTXT)) {
                    Toast.makeText(RegisterActivity.this, "Username must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(confirmpasswordTXT)) {
                    Toast.makeText(RegisterActivity.this, "Username must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (passwordTXT.equals(confirmpasswordTXT)) {
                    Boolean userExisting = user_db.getAllUsers().contains(username.toString());
                    if(userExisting==false) {
                        user_db.addUser(new User(usernameTXT, passwordTXT));
                        Toast.makeText(RegisterActivity.this, "Profile Created", Toast.LENGTH_SHORT).show();
                        Intent homeIntent = new Intent(RegisterActivity.this, HomeScreene.class);
                        startActivity(homeIntent);
                    }else
                        Toast.makeText(RegisterActivity.this, "Username Taken", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(RegisterActivity.this, "Your passwords didnt match", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });



    }
}