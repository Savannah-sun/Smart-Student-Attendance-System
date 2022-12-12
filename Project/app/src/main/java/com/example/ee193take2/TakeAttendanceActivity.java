package com.example.ee193take2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ee193take2.databinding.ActivityTakeAttendanceBinding;

public class TakeAttendanceActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText student_Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);
        student_Name = findViewById(R.id.className);


        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mClass_Name.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String className = mClass_Name.getText().toString();
                Boolean status = mStatus.isChecked();
                String strOfferings = mNumOfferings.getText().toString();
                int numOfferings = Integer.parseInt(strOfferings);

                replyIntent.putExtra("class_name", className)
                        .putExtra("status",status)
                        .putExtra("numOfferings",numOfferings);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}