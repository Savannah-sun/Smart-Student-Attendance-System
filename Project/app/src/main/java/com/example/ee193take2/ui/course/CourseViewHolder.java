package com.example.ee193take2.ui.course;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ee193take2.R;

class CourseViewHolder extends RecyclerView.ViewHolder {

    private final TextView courseItemView;

    private CourseViewHolder(View itemView){
        super(itemView);
        courseItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        courseItemView.setText(text);
    }

    static CourseViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recylclerview_item,parent,false);

        return new CourseViewHolder(view);
    }


}
