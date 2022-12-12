package com.example.ee193take2.ui.course;

import static android.app.PendingIntent.getActivity;
import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ee193take2.Class_Offerings_Activity;
import com.example.ee193take2.NewCourseActivity;
import com.example.ee193take2.NewStudentActivity;
import com.example.ee193take2.R;
import com.example.ee193take2.databinding.FragmentClassHomeBinding;
import com.example.ee193take2.ui.database.Course;
import com.example.ee193take2.ui.database.DBViewModel;
import com.example.ee193take2.ui.database.Student;

class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView courseItemView;

    private CourseViewHolder(View itemView){
        super(itemView);
        courseItemView = itemView.findViewById(R.id.textView);
        itemView.setOnClickListener(this);
    }

    public void bind(String text) {
        courseItemView.setText(text);
    }

    static CourseViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recylclerview_item,parent,false);

        return new CourseViewHolder(view);
    }


    @Override
    public void onClick(View v){
        String course_name = this.courseItemView.getText().toString();
        //Launch Activity..
        Context currActivity = this.courseItemView.getContext();
        Intent intent = new Intent(currActivity, Class_Offerings_Activity.class);
        intent.putExtra("course_name" , course_name);
        currActivity.startActivity(intent);


    }



}
