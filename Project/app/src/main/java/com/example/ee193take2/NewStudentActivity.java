package com.example.ee193take2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class NewStudentActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditWordView, mEditLastName,mEditEmail,mEditStatus,mEditPayment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);
        mEditWordView = findViewById(R.id.edit_word);
        mEditLastName = findViewById(R.id.edit_last_name);
        mEditEmail = findViewById(R.id.email);
        mEditStatus = findViewById(R.id.status);
        mEditPayment = findViewById(R.id.payment);


        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditWordView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String name = mEditWordView.getText().toString();
                String lastName = mEditLastName.getText().toString();
                String email = mEditEmail.getText().toString();
                String status = mEditStatus.getText().toString();
                String payment = mEditPayment.getText().toString();

                String[] student_info = {name,lastName,email,status,payment};
                replyIntent.putExtra(EXTRA_REPLY, student_info);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}
