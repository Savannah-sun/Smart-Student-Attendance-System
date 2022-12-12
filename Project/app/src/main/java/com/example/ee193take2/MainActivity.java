package com.example.ee193take2;


import android.app.Activity;

import androidx.fragment.app.Fragment;


//import android.app.FragmentTransaction;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ee193take2.ui.database.Course;
import com.example.ee193take2.ui.database.Student;
import com.example.ee193take2.ui.database.DBViewModel;
import com.example.ee193take2.ui.student.StudentListAdapter;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ee193take2.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

import com.example.ee193take2.databinding.FragmentClassInfoBinding;
import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private DBViewModel dbViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        SQLiteStudioService.instance().start(this);

        RecyclerView recyclerView = findViewById((R.id.recyclerview));
        final StudentListAdapter adapter = new StudentListAdapter(new StudentListAdapter.StudentDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbViewModel = new ViewModelProvider(this).get(DBViewModel.class);

        dbViewModel.getAllStudents().observe(this, students -> {
            adapter.submitList(students);
        });

        dbViewModel.deleteAllStudents();
        dbViewModel.deleteAllCourse();

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, login_activity.class);
                login.launch(intent);
            }
        });



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( view -> {
            Intent intent =new Intent(MainActivity.this, NewStudentActivity.class);
            NewStudentActivityResultLauncher.launch(intent);
        });

        FloatingActionButton hide = findViewById(R.id.hide);
        hide.setOnClickListener( view -> {
            if(recyclerView.getVisibility() == View.VISIBLE){
                recyclerView.setVisibility(View.INVISIBLE);
            }
            else{
                recyclerView.setVisibility(View.VISIBLE);
            }
        });

    }

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
                    } else {
                        Toast.makeText(
                                getApplicationContext(),
                                R.string.empty_not_saved,
                                Toast.LENGTH_LONG).show();
                    }
                }
            });


    ActivityResultLauncher<Intent> login = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult results) {
                    if (results.getResultCode() == Activity.RESULT_OK) {
                        Log.d("Debug", "Return Results");
                        Intent data = results.getData();
                        String login_result =  data.getStringArrayExtra(NewStudentActivity.EXTRA_REPLY)[0];

                        Log.d("Debug", login_result);

                        if (login_result.equals("true"))
                        {
                            ((ConstraintLayout) findViewById(R.id.replaceContainer)).removeAllViews();
                            Class_Home add_class = new Class_Home();
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.replaceContainer, add_class);
                            transaction.commit();
                        }
                    }
                }
            });
}



