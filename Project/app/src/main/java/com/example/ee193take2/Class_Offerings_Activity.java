package com.example.ee193take2;

import android.content.Intent;
import android.os.Bundle;

import com.example.ee193take2.ui.database.Course;
import com.example.ee193take2.ui.database.DBViewModel;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ee193take2.databinding.ActivityClassOfferingsBinding;

public class Class_Offerings_Activity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityClassOfferingsBinding binding;

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mClass_Name,mNumOfferings;
    private CheckBox mStatus;
    private DBViewModel dbViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityClassOfferingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setContentView(R.layout.activity_class_offerings);

        dbViewModel = new ViewModelProvider(this).get(DBViewModel.class);


//        mClass_Name = findViewById(R.id.className);
//        mStatus = findViewById(R.id.course_status);
//        mNumOfferings = findViewById(R.id.numOfferings);

        String course_name = getIntent().getStringExtra("course_name");
        Log.d("here", course_name);


        TextView text = findViewById(R.id.test_text);
        text.setText(course_name);

        LiveData<Course> this_course = dbViewModel.getCourseByName(course_name);




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


}