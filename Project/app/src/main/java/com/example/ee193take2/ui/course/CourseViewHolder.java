package com.example.ee193take2.ui.course;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ee193take2.R;

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
        Log.d("Testing", this.courseItemView.getText().toString());
        //Launch Activity..


    }


}
