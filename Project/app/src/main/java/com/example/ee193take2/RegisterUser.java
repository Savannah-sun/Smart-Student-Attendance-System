package com.example.ee193take2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{
    private TextView banner;
    private EditText name, school, email, password;
    private Button register;
    private ProgressBar bar;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        name = (EditText) findViewById(R.id.RegisterUsername);
        school = (EditText) findViewById(R.id.School);
        email = (EditText) findViewById(R.id.RegisterEmail);
        password = (EditText) findViewById(R.id.textPassword);

        register = (Button) findViewById(R.id.Register);
        banner = (TextView) findViewById(R.id.banner);
        bar = (ProgressBar) findViewById(R.id.progressBar2);

        banner.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Register:
                registerUser();
                break;
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    private void registerUser() {
        String aemail = email.getText().toString().trim();
        String aname = name.getText().toString().trim();
        String apassword = password.getText().toString().trim();
        String aschool = school.toString().toString().trim();
        if(aname.isEmpty()){
            name.setError("Name is required!");
            name.requestFocus();
            return;
        }
        if(aschool.isEmpty()){
            school.setError("School is required!");
            school.requestFocus();
            return;
        }
        if(aemail.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(aemail).matches()){
            email.setError("Please provide a correct email address");
            email.requestFocus();
            return;
        }

        user = new User(aemail, apassword);
        Toast.makeText(RegisterUser.this,"User has been registered successfully!",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, MainActivity.class));
    }
}