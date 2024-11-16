package com.example.bakery;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText etUsername = findViewById(R.id.et_username);
        EditText etPassword = findViewById(R.id.et_password_input);
        Button btnLogin = findViewById(R.id.btn_login);
        Button btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Register button clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });
        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            if (username.equals("admin") && password.equals("admin")) {
                Intent intent = new Intent(MainActivity.this, AdminHomeActivity.class);
                startActivity(intent);

            } else {
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseHelper dbhelper = new DatabaseHelper(MainActivity.this);
                    boolean result = dbhelper.checkUserByUsername(username, password);
                    if (result) {

                        Intent intent = new Intent(MainActivity.this, ProductsDisplay.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Welcome To BAKERY HUB!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid username & password!", Toast.LENGTH_SHORT).show();
                    }

                }
            }

        });


    }
}