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

import com.example.ee193take2.Class_Offerings_Activity;
import com.example.ee193take2.R;
import com.example.ee193take2.TakeAttendanceActivity;

public class StudentViewHolder extends RecyclerView.ViewHolder {

    private final TextView studentItemView;
    private final CheckBox checkBox;

    private StudentViewHolder(View itemView){
        super(itemView);
        studentItemView = itemView.findViewById(R.id.textView);
        checkBox =itemView.findViewById(R.id.present_box);
    }

    public void bind(String text) {
        studentItemView.setText(text);
    }

    static StudentViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_recylclerview_item,parent,false);

        return new StudentViewHolder(view);
    }

    @Override
    public void onClick(View v){
        String name = this.studentItemView.getText().toString();
        Log.d("hereeee", name);
        //Launch Activity..
        Context currActivity = this.studentItemView.getContext();
        Intent intent = new Intent(currActivity, TakeAttendanceActivity.class); // need to create the activity for this
        intent.putExtra("present" , name);
        currActivity.startActivity(intent);
    }


}
