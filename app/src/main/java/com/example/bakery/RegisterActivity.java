package com.example.bakery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText etUsername = findViewById(R.id.et_register_username);
        EditText etEmail = findViewById(R.id.et_register_email);
        EditText etPassword = findViewById(R.id.et_register_password);
        EditText etConfirmPassword = findViewById(R.id.et_register_confirm_password);
        EditText etMobile = findViewById(R.id.et_register_mobile);
        Button btnLogin = findViewById(R.id.btn_login);
        Button btnRegister = findViewById(R.id.btn_register);


        btnRegister.setOnClickListener(v -> {
                    String username = etUsername.getText().toString().trim();
                    String email = etEmail.getText().toString().trim();
                    String password = etPassword.getText().toString();
                    String confirmPassword = etConfirmPassword.getText().toString();
                    String mobile = etMobile.getText().toString();

                    Pattern phonePattern=Pattern.compile("^(\\+88)?01[2-9][0-9]{8}$");


                    if (username.isEmpty() || email.isEmpty() || mobile.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                        Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    else if (!password.equals(confirmPassword)) {
                        Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else if(!phonePattern.matcher(mobile).matches())
                    {
                        Toast.makeText(RegisterActivity.this, "Invalid phone number", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Successfully sign up ", Toast.LENGTH_SHORT).show();


                        DatabaseHelper dbHelper = new DatabaseHelper(RegisterActivity.this);
                        boolean isInserted = dbHelper.insertUser(username, email, mobile, password);

                        if (isInserted) {
                            Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Not registered please try again!!", Toast.LENGTH_SHORT).show();

                        }
                    }


          }
        );



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this, "Login button clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}