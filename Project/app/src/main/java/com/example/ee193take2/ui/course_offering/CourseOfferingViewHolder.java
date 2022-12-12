package com.example.ee193take2.ui.course_offering;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ee193take2.Class_Offerings_Activity;
import com.example.ee193take2.CourseOfferingDisplayActivity;
import com.example.ee193take2.R;
import com.example.ee193take2.ui.database.DBViewModel;

class CourseOfferingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView courseItemView;
    private Context ctx;

    private CourseOfferingViewHolder(View itemView){
        super(itemView);
        courseItemView = itemView.findViewById(R.id.textView);
        itemView.setOnClickListener(this);

    }

    public void bind(String text) {
        courseItemView.setText(text);
    }

    static CourseOfferingViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recylclerview_item,parent,false);

        return new CourseOfferingViewHolder(view);
    }


    @Override
    public void onClick(View v){
        Log.d("Testing","Course offering clicked");
        String string = this.courseItemView.getText().toString();
        String[] strArray = string.split(" ");
        Context currActivity = this.courseItemView.getContext();
        Intent intent = new Intent(currActivity, CourseOfferingDisplayActivity.class);
        intent.putExtra("course_id", Integer.parseInt(strArray[0]));
        intent.putExtra("classroom",strArray[2]);
        currActivity.startActivity(intent);

    }



}
