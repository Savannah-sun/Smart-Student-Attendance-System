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
                break;
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }


}