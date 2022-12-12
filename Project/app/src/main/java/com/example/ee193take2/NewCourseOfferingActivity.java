package com.example.ee193take2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.example.ee193take2.ui.database.CourseOffering;
import com.example.ee193take2.ui.database.DBViewModel;
import com.example.ee193take2.ui.database.Student;
import com.example.ee193take2.ui.student.StudentListAdapter;

public class NewCourseOfferingActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditCourseID,mEditClassroomNum,mEditNumStudents;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_new_course_offering);
        mEditCourseID = findViewById(R.id.course_id);
        mEditClassroomNum = findViewById(R.id.classroom_number);
        mEditNumStudents = findViewById(R.id.num_students);


        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditCourseID.getText()) || TextUtils.isEmpty(mEditClassroomNum.getText()) || TextUtils.isEmpty(mEditNumStudents.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                int course_id = Integer.parseInt(mEditCourseID.getText().toString());
                int num_students = Integer.parseInt(mEditNumStudents.getText().toString());
                String classroom_num = mEditClassroomNum.getText().toString();

                replyIntent.putExtra("course_id", course_id)
                        .putExtra("num_students", num_students)
                        .putExtra("classroom_num", classroom_num);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}