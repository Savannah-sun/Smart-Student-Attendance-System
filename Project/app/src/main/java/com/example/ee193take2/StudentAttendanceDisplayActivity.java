package com.example.ee193take2;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.ee193take2.databinding.ActivityClassOfferingDisplayBinding;
import com.example.ee193take2.ui.database.Attendance;
import com.example.ee193take2.ui.database.DBViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class StudentAttendanceDisplayActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityClassOfferingDisplayBinding binding;

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    private Random random;
    private EditText mClass_Name,mNumOfferings;
    private CheckBox mStatus;
    private DBViewModel dbViewModel;
    private LifecycleOwner ctx;
    CalendarView cal;
    TextView student_name, course_offering, attendance_percent,last_missed;

    private int course_id,studentId,course_offering_id;
    private String studentFirst, studentLast;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        random = new Random();
        super.onCreate(savedInstanceState);
        ctx = this;


        binding = ActivityClassOfferingDisplayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setContentView(R.layout.activity_student_attendance);

        dbViewModel = new ViewModelProvider(this).get(DBViewModel.class);

        //get intentions
        studentId = getIntent().getIntExtra("student_id",0);
        course_id = getIntent().getIntExtra("course_id", 0);
        course_offering_id = getIntent().getIntExtra("course_offering_id", 0);
        studentFirst = getIntent().getStringExtra("student_first");
        studentLast = getIntent().getStringExtra("student_last");
        //get all views
        student_name = findViewById(R.id.student_name);
        course_offering = findViewById(R.id.course_name);
        attendance_percent = findViewById(R.id.present_percent);
        last_missed = findViewById(R.id.last_missed_class);
        //set views
        student_name.setText(studentLast + " " + studentFirst);
        dbViewModel.getCourseByID(course_id).observe(this, course ->{
            course_offering.setText(course.getClass_name());
        });

        dbViewModel.getAllAttendanceByStudentIDDateCourseOffering(studentId,course_offering_id).observe(this, attendList -> {
            if(attendList.size() < 1){
                attendance_percent.setText("N/A (Student Has Yet to Attend Class)");
            }
            else{
                int counter = 0;
                for(Boolean present: attendList){
                    if(present){
                        counter+=1;
                    }
                }
                attendance_percent.setText("Attendance Percent: " + Integer.toString(((counter)*100)/attendList.size()) + "%");
            }
        });

        dbViewModel.getAttendanceByStudentID(studentId,course_offering_id).observe(this, attendList -> {
            Date missedDate =new Date(0);
            Boolean dispFlag = Boolean.FALSE;
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");

            for(Attendance attend: attendList){
                if(!attend.isAttend()){
                    if(attend.getDate().after(missedDate));
                        dispFlag = Boolean.TRUE;
                        missedDate = attend.getDate();
                }
            }
            if(dispFlag) {
                last_missed.setText("Last Missed Class On:" + sdf.format(missedDate));
            }
        });

    }
}
