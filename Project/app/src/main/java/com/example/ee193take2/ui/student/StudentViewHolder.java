package com.example.ee193take2.ui.student;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ee193take2.CourseOfferingDisplayActivity;
import com.example.ee193take2.R;
import com.example.ee193take2.StudentAttendanceDisplayActivity;
import com.example.ee193take2.ui.database.CourseOffering;
import com.example.ee193take2.ui.database.Student;

public class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView studentItemView;
    private final CheckBox checkBox;
    private Student student;
    private CourseOffering courseOffering;

    private StudentViewHolder(View itemView){
        super(itemView);
        studentItemView = itemView.findViewById(R.id.textView);
        checkBox =itemView.findViewById(R.id.present_box);
        itemView.setOnClickListener(this);
    }

    public void bind(String text) {
        studentItemView.setText(text);
    }

    public void setStudent(Student student){
        this.student = student;
    }

    public void setCourseOffering(CourseOffering courseOffering){
        this.courseOffering = courseOffering;
    }



    static StudentViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_recylclerview_item,parent,false);

        return new StudentViewHolder(view);
    }

    @Override
    public void onClick(View v){
        Log.d("Testing","Course offering clicked");
        Context currActivity = this.studentItemView.getContext();
        Intent intent = new Intent(currActivity, StudentAttendanceDisplayActivity.class);
        intent.putExtra("student_id", student.getStudent_id());
        intent.putExtra("course_id", courseOffering.getCourse_id());
        intent.putExtra("course_offering_id",courseOffering.getCid());
        intent.putExtra("student_first", student.getFirstName());
        intent.putExtra("student_last",student.getLastName());
        currActivity.startActivity(intent);

    }

}
