package com.example.ee193take2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GeneralListAdapter extends RecyclerView.Adapter<GeneralListAdapter.GListViewHolder> {

    private ArrayList<String> course_offerings;
    private OnItemClickListener onItemClickListener;
    private Context context;

    @NonNull
    @Override
    public GListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_listing, parent, false);
        return new GListViewHolder(itemView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GListViewHolder holder, int position) {
        String course_offering = course_offerings.get(position);
        holder.course_name.setText(course_offering);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    // constructor
    public GeneralListAdapter(ArrayList<String> offerings, Context context) {
        this.course_offerings = offerings;
        this.context = context;
    }

    public static class GListViewHolder extends RecyclerView.ViewHolder {
        TextView course_name;

        public GListViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            course_name = itemView.findViewById(R.id.course_listing); // need to create this xml
            itemView.setOnClickListener(v->onItemClickListener.onClick(getAdapterPosition()));
        }
    }

}














