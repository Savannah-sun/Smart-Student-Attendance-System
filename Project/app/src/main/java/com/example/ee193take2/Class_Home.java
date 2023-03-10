package com.example.ee193take2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.ee193take2.databinding.ActivityMainBinding;
import com.example.ee193take2.databinding.FragmentClassHomeBinding;
import com.example.ee193take2.ui.course.CourseListAdapter;
import com.example.ee193take2.ui.database.Course;
import com.example.ee193take2.ui.database.DBViewModel;
import com.example.ee193take2.ui.database.Student;
import com.example.ee193take2.ui.student.StudentListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.Random;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;


public class Class_Home extends Fragment {

    FragmentClassHomeBinding binding;
    private RecyclerView courseView;
    Context thisContext;
    private DBViewModel dbViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private View rootView;
    private CourseListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentClassHomeBinding.inflate(inflater, container, false);
        rootView = inflater.inflate(R.layout.fragment_class__home ,container, false);
        thisContext = container.getContext();


        courseView = rootView.findViewById((R.id.courseView));
        courseView.setHasFixedSize(true);
        courseView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        adapter = new CourseListAdapter(new CourseListAdapter.CourseDiff());
        courseView.setAdapter(adapter);

        dbViewModel = new ViewModelProvider(this).get(DBViewModel.class);


        Button add_class = rootView.findViewById(R.id.add_class_button);
        add_class.setOnClickListener( view -> {
            Intent intent =new Intent(getActivity(), NewCourseActivity.class);
            CourseActivityLauncher.launch(intent);
        });

        Button expert_attendance = rootView.findViewById(R.id.add_class_button2);
        expert_attendance.setOnClickListener( view -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            i.setType("message/rfc822");

            String filename = "app_database";
            String pathname = thisContext.getApplicationInfo().dataDir + "/databases";
            String fileLocation = pathname + "/" + filename;
            File data = new File(fileLocation);
            Uri uri = FileProvider.getUriForFile(thisContext, "com.example.ee193take2.provider", data);
            i.putExtra(Intent.EXTRA_SUBJECT, "All information");
            i.putExtra(Intent.EXTRA_TEXT   , "The database file of Smart Student Attendance System is in the attachment, please feel free to reach me if you have any questions");
            i.putExtra(Intent.EXTRA_STREAM, uri);
            startActivity(Intent.createChooser(i, "All Data has been sent"));
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);



        dbViewModel.insertCourse(new Course(1, "CS-101", true, 1));
        dbViewModel.insertCourse(new Course(2, "EE-101", true, 1));


        dbViewModel.getAlphabetizedCourses().observe(getViewLifecycleOwner(), courses -> {
            Log.d("STATE", "onCreateView: " + courses.toString());
            adapter.notifyDataSetChanged();
            adapter.submitList(courses);
        });


    }



    ActivityResultLauncher<Intent> CourseActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult results) {
                    if (results.getResultCode() == Activity.RESULT_OK) {
                        Intent data = results.getData();
                        Random random = new Random();
                        Course course = new Course(random.nextInt(1000), data.getStringExtra("class_name"),data.getBooleanExtra("status",true),data.getIntExtra("numOfferings",0));
                        dbViewModel.insertCourse(course);
                    } else {
                        Toast.makeText(
                                thisContext,
                                R.string.empty_not_saved,
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

}