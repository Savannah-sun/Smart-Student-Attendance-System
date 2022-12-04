package com.example.ee193take2.ui.student;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ee193take2.R;
import com.example.ee193take2.Student;

class StudentViewHolder extends RecyclerView.ViewHolder {

    private final TextView studentItemView;

    private StudentViewHolder(View itemView){
        super(itemView);
        studentItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        studentItemView.setText(text);
    }

    static StudentViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recylclerview_item,parent,false);

        return new StudentViewHolder(view);
    }


}
