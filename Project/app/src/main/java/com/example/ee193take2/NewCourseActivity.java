package com.example.ee193take2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class NewCourseActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mClass_Name,mNumOfferings;
    private CheckBox mStatus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);
        mClass_Name = findViewById(R.id.className);
        mStatus = findViewById(R.id.course_status);
        mNumOfferings = findViewById(R.id.numOfferings);



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
