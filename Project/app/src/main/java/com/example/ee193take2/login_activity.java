package com.example.ee193take2;

import static com.example.ee193take2.NewStudentActivity.EXTRA_REPLY;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class login_activity extends AppCompatActivity {


    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.edit_word_username);
        password = findViewById(R.id.edit_word_password);

        Log.d("Debug", "Created!!!!! -------");

        final Button button = findViewById(R.id.button_save_login);
        button.setOnClickListener(view -> {
            Log.d("Debug", "Clicked!!!!! -------");
            Log.d("Debug", username.getText().toString());
            Log.d("Debug", password.getText().toString());


            if (username.getText().toString().equals("username")){
                if (password.getText().toString().equals("password")){

                    Log.d("Debug", "Login TRUE");

                    Intent replyIntent = new Intent();
                    String[] login_info = {"true"};
                    replyIntent.putExtra(EXTRA_REPLY, login_info);
                    setResult(RESULT_OK, replyIntent);



//                    Log.d("Debug", "here");
////                    Change Fragment
//                    Class_Home class_info = new Class_Home();
//                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                    transaction.replace(R.id.replaceContainer, class_info);
//                    transaction.commit();

//                    Intent intent = new Intent(login_activity.this, NewStudentActivity.class);
//                    startActivity(intent);
                    finish();
                }
            }

//            finish();
        });


    }
}