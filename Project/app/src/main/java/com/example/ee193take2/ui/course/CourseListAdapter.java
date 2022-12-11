package com.example.ee193take2.ui.course;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.ee193take2.ui.database.Course;


public class CourseListAdapter extends ListAdapter<Course, CourseViewHolder> {


    public CourseListAdapter(@NonNull DiffUtil.ItemCallback<Course> diffCallback){
        super(diffCallback);
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CourseViewHolder.create(parent);
    }


    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position){
        Course current = getItem(position);
        holder.bind(current.getClass_name())
        ;
    }

    public static class CourseDiff extends DiffUtil.ItemCallback<Course>{

        @Override
        public boolean areItemsTheSame(@NonNull Course oldItem, @NonNull Course newItem){
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Course oldItem, @NonNull Course newItem){
            return oldItem.getClass_name().equals(newItem.getClass_name());
        }
    }

}
