package com.example.ee193take2;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ee193take2.ui.database.DAO;
import com.example.ee193take2.ui.database.DAOdatabase;
import com.example.ee193take2.ui.database.Student;
import com.example.ee193take2.ui.student.StudentListAdapter;
import com.example.ee193take2.ui.student.StudentViewModel;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.Room;

import com.example.ee193take2.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private StudentViewModel mStudentViewModel;


    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DAOdatabase db = DAOdatabase.getInstance(getApplicationContext());

//        RecyclerView recyclerView = findViewById((R.id.recyclerview));
//        final StudentListAdapter adapter = new StudentListAdapter(new StudentListAdapter.StudentDiff());
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        mStudentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
//
//        mStudentViewModel.getAllStudents().observe(this, students -> {
//            adapter.submitList(students);
//        });
//
//
//        //mStudentViewModel.deleteAll();
//
//
//        binding.startButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ((ConstraintLayout) findViewById(R.id.replaceContainer)).removeAllViews();
//                Class_Home frag1 = new Class_Home();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.replaceContainer, frag1);
//                transaction.commit();
//            }
//        });
//
//        binding.buttonHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ((ConstraintLayout) findViewById(R.id.replaceContainer)).removeAllViews();
//                Class_Home class_info = new Class_Home();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.replaceContainer, class_info);
//                transaction.commit();
//            }
//        });

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener( view -> {
//            Intent intent =new Intent(MainActivity.this, NewStudentActivity.class);
//            NewStudentActivityResultLauncher.launch(intent);
//        });
//
//        FloatingActionButton hide = findViewById(R.id.hide);
//        hide.setOnClickListener( view -> {
//            if(recyclerView.getVisibility() == View.VISIBLE){
//                recyclerView.setVisibility(View.INVISIBLE);
//            }
//            else{
//                recyclerView.setVisibility(View.VISIBLE);
//            }
//        });
//
//    }

//    ActivityResultLauncher<Intent> NewStudentActivityResultLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult results) {
//                    if (results.getResultCode() == Activity.RESULT_OK) {
//                        Intent data = results.getData();
//                        Student student = new Student(data.getStringArrayExtra(NewStudentActivity.EXTRA_REPLY)[1], data.getStringArrayExtra(NewStudentActivity.EXTRA_REPLY)[0]);
//                        mStudentViewModel.insertStudent(student);
//                    } else {
//                        Toast.makeText(
//                                getApplicationContext(),
//                                R.string.empty_not_saved,
//                                Toast.LENGTH_LONG).show();
//                    }
//                }
//            });

    }
}