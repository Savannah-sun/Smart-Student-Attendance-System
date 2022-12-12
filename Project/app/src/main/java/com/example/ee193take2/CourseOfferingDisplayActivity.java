package com.example.ee193take2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.ee193take2.databinding.ActivityClassOfferingDisplayBinding;
import com.example.ee193take2.ui.database.Course;
import com.example.ee193take2.ui.database.CourseOffering;
import com.example.ee193take2.ui.database.DBViewModel;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ee193take2.ui.database.Student;
import com.example.ee193take2.ui.database.StudentClassOffering;
import com.example.ee193take2.ui.student.StudentListAdapter;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class CourseOfferingDisplayActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityClassOfferingDisplayBinding binding;

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    private Random random;
    private EditText mClass_Name,mNumOfferings;
    private CheckBox mStatus;
    private DBViewModel dbViewModel;
    private LifecycleOwner ctx;
    int course_id;
    AtomicInteger cid = new AtomicInteger();
    String classroom;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        random = new Random();
        super.onCreate(savedInstanceState);
        ctx= this;

        binding = ActivityClassOfferingDisplayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setContentView(R.layout.activity_class_offering_display);

        dbViewModel = new ViewModelProvider(this).get(DBViewModel.class);

        AtomicReference<String> class_name = new AtomicReference<String>();

        course_id = getIntent().getIntExtra("course_id", 0);
        classroom = getIntent().getStringExtra("classroom");
        Log.d("here", Integer.toString(course_id));


        TextView text = findViewById(R.id.test_text);

        LiveData<Course> this_course = dbViewModel.getCourseByID(course_id);

        this_course.observe(this, course -> {
            class_name.set(course.getClass_name());
            text.setText(class_name.toString());

        });

        RecyclerView recyclerView = findViewById((R.id.recyclerView2));
        final StudentListAdapter adapter = new StudentListAdapter(new StudentListAdapter.StudentDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LiveData<List<CourseOffering>> course_list = dbViewModel.getCourseOfferingByCourseID(course_id);

        course_list.observe(this, courselist -> {
            for (CourseOffering course: courselist) {
                if (course.getClassroom().equals(classroom)) {
                    cid.set(course.getCid());
                    dbViewModel.getStudentByClassOfferingID(cid.intValue()).observe(this, students -> {
                        adapter.submitList(students);
                    });
                }
            }
        });


        Button add_course_offering = findViewById(R.id.addStudent);
        add_course_offering.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewStudentActivity.class);
            NewStudentActivityResultLauncher.launch(intent);
        });
    }

//        final Button button = findViewById(R.id.button_save);
//        button.setOnClickListener(view -> {
//            Intent replyIntent = new Intent();
//            if (TextUtils.isEmpty(mClass_Name.getText())) {
//                setResult(RESULT_CANCELED, replyIntent);
//            } else {
//                String className = mClass_Name.getText().toString();
//                Boolean status = mStatus.isChecked();
//                String strOfferings = mNumOfferings.getText().toString();
//                int numOfferings = Integer.parseInt(strOfferings);
//
//                replyIntent.putExtra("class_name", className)
//                        .putExtra("status",status)
//                        .putExtra("numOfferings",numOfferings);
//                setResult(RESULT_OK, replyIntent);
//            }
//            finish();
//        });


    ActivityResultLauncher<Intent> NewStudentActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult results) {
                    if (results.getResultCode() == Activity.RESULT_OK) {
                        Intent data = results.getData();
                        Student student = new Student(random.nextInt(1000),data.getStringArrayExtra(NewStudentActivity.EXTRA_REPLY)[1], data.getStringArrayExtra(NewStudentActivity.EXTRA_REPLY)[0],
                                data.getStringArrayExtra(NewStudentActivity.EXTRA_REPLY)[2], data.getStringArrayExtra(NewStudentActivity.EXTRA_REPLY)[3], data.getStringArrayExtra(NewStudentActivity.EXTRA_REPLY)[4]);
                        dbViewModel.insertStudent(student);
                        dbViewModel.getCourseOfferingByCourseID(course_id).observe(ctx, courselist ->{
                            Log.d("TEST", classroom);
                            for (CourseOffering course: courselist) {
                                Log.d("TEST", course.getClassroom());
                                if (course.getClassroom().equals(classroom)) {
                                    cid.set(course.getCid());
                                    dbViewModel.insertStudentAndClassOffering(new StudentClassOffering(student.getStudent_id(), cid.intValue()));
                                }
                            }
                        });

                    } else {
                        Toast.makeText(
                                getApplicationContext(),
                                R.string.empty_not_saved,
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

}

