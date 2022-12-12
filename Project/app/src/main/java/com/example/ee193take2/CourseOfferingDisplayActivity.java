package com.example.ee193take2;

import android.app.Activity;
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

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ee193take2.ui.student.StudentListAdapter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class CourseOfferingDisplayActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityClassOfferingDisplayBinding binding;

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mClass_Name,mNumOfferings;
    private CheckBox mStatus;
    private DBViewModel dbViewModel;
    AtomicInteger course_id = new AtomicInteger();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityClassOfferingDisplayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setContentView(R.layout.activity_class_offering_display);

        dbViewModel = new ViewModelProvider(this).get(DBViewModel.class);

        AtomicReference<String> class_name = new AtomicReference<String>();

        int course_id = getIntent().getIntExtra("course_id",0);
        Log.d("here", Integer.toString(course_id));


        TextView text = findViewById(R.id.test_text);

        LiveData<Course> this_course = dbViewModel.getCourseByID(course_id);

        this_course.observe(this, course ->{
            class_name.set(course.getClass_name());
            text.setText(class_name.toString());
        });

        int cid = 1;



        RecyclerView recyclerView = findViewById((R.id.recyclerView2));
        final StudentListAdapter adapter = new StudentListAdapter(new StudentListAdapter.StudentDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbViewModel = new ViewModelProvider(this).get(DBViewModel.class);

        dbViewModel.getStudentByClassOfferingID(cid).observe(this, students -> {
            adapter.submitList(students);
        });



        Button add_course_offering = findViewById(R.id.addStudent);
//        add_course_offering.setOnClickListener( view -> {
//            Intent intent =new Intent(this, NewCourseOfferingActivity.class);
//            NewCourseOfferingActivityLauncher.launch(intent);
//        });

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
    }

//    ActivityResultLauncher<Intent> NewCourseOfferingActivityLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult results) {
//                    if (results.getResultCode() == Activity.RESULT_OK) {
//                        Log.d("Debug", "Return Results");
//                        Intent data = results.getData();
//                        int num_students =  data.getIntExtra("num_students",0);
//                        String classroom_name = data.getStringExtra("classroom_num");
//
//                        CourseOffering course_off = new CourseOffering(course_id.intValue(),classroom_name,num_students);
//                        dbViewModel.insertClassOffering(course_off);
//                    }
//                }
//            });

}