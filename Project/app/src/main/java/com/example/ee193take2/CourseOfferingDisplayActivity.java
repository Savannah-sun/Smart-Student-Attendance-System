package com.example.ee193take2;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.ee193take2.databinding.ActivityClassOfferingDisplayBinding;
import com.example.ee193take2.ui.database.Attendance;
import com.example.ee193take2.ui.database.Course;
import com.example.ee193take2.ui.database.CourseOffering;
import com.example.ee193take2.ui.database.DBViewModel;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
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
import com.example.ee193take2.ui.student.StudentViewHolder;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
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
    CalendarView cal;
    TextView date_text;

    int course_id;
    AtomicInteger cid = new AtomicInteger();
    String classroom;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        random = new Random();
        super.onCreate(savedInstanceState);
        ctx= this;
        final Date[] date = new Date[1];
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");

        binding = ActivityClassOfferingDisplayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setContentView(R.layout.activity_class_offering_display);

        dbViewModel = new ViewModelProvider(this).get(DBViewModel.class);

        AtomicReference<String> class_name = new AtomicReference<String>();

        //get intentions
        course_id = getIntent().getIntExtra("course_id", 0);
        classroom = getIntent().getStringExtra("classroom");

        //get ui elements
        cal = findViewById(R.id.calendarView2);
        cal.setVisibility(View.GONE);
        TextView text = findViewById(R.id.test_text);
        date_text = findViewById(R.id.date_text);
        date_text.setText(dateFormat.format((cal.getDate())));
        date[0] = new Date(cal.getDate());

        //get class name for the purposes of displaying it at the top of the screen
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


        Button add_student = findViewById(R.id.addStudent);
        add_student.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewStudentActivity.class);
            NewStudentActivityResultLauncher.launch(intent);
        });
        //Change date selection updates locally stored date value
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                date[0] = new Date(year,month,day);
                date_text.setText((dateFormat.format(date[0])));
            }
        });

        //change date button
        Button change_date = findViewById(R.id.selectDate);
        change_date.setOnClickListener(view -> {
            if(cal.getVisibility() == view.GONE) {
                change_date.setText("Close Calendar");
                cal.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
            }
            else {
                change_date.setText("Change Date");
                cal.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

        });

        Button take_attendance = findViewById(R.id.take_attendance);
        take_attendance.setOnClickListener(view ->{
            CheckBox checkBox;
            Student current_student;
            for(int i =0; i< recyclerView.getChildCount(); i++){
                checkBox = (recyclerView.getChildAt(i).findViewById(R.id.present_box));
                current_student  = adapter.getCurrentList().get(i);
                Log.d("TESTING" , checkBox.toString() + "\n" + current_student.toString());

                dbViewModel.insertAttendance(new Attendance(current_student.getStudent_id(), cid.intValue(), new java.sql.Date(date[0].getTime()),checkBox.isChecked()));
                Toast.makeText(
                        getApplicationContext(),
                        "Attendance Taken Successfully!",
                        Toast.LENGTH_LONG).show();
            }
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

