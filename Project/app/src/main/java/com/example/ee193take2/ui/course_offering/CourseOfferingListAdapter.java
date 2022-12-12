package com.example.ee193take2.ui.course_offering;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.ee193take2.ui.database.Course;
import com.example.ee193take2.ui.database.CourseOffering;


public class CourseOfferingListAdapter extends ListAdapter<CourseOffering, CourseOfferingViewHolder> {


    public CourseOfferingListAdapter(@NonNull DiffUtil.ItemCallback<CourseOffering> diffCallback){
        super(diffCallback);
    }

    @Override
    public CourseOfferingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CourseOfferingViewHolder.create(parent);
    }


    @Override
    public void onBindViewHolder(CourseOfferingViewHolder holder, int position){
        CourseOffering current = getItem(position);
        holder.bind( Integer.toString(current.getCourse_id()) + " Rm " + current.getClassroom());
    }

    public static class CourseDiff extends DiffUtil.ItemCallback<CourseOffering>{

        @Override
        public boolean areItemsTheSame(@NonNull CourseOffering oldItem, @NonNull CourseOffering newItem){
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull CourseOffering oldItem, @NonNull CourseOffering newItem){
            return oldItem.getClassroom().equals(newItem.getClassroom());
        }
    }

}
