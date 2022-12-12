package com.example.ee193take2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.ee193take2.databinding.FragmentClassInfoBinding;

public class Class_Info extends Fragment {

    FragmentClassInfoBinding binding;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentClassInfoBinding.inflate(inflater, container, false);
        rootView = inflater.inflate(R.layout.fragment_class__home ,container, false);

//        /* Text Field For Class Name */
//
//
        /* Offering List */
        binding.buttonNewOffering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              pass class id in the instructor?
                Class_Offerings class_offer = new Class_Offerings();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.replaceContainer, class_offer);
                transaction.commit();
            }
        });

//        Button add_course_offering = rootView.findViewById(R.id.add_class_button);
//        add_class.setOnClickListener( view -> {
//            Intent intent =new Intent(getActivity(), NewCourseActivity.class);
//            CourseOfferingActivityLauncher.launch(intent);
//        });
//
//
//
//        /* Statistics */
//        /* Offering List */
//        binding.buttonStatistics.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                class_Statistics class_stats = new class_Statistics();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.replaceContainer, class_stats);
//                transaction.commit();
//            }
//        });
//
//
//
//        /* Students */
//        binding.buttonStudents.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Class_Students class_students = new Class_Students();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.replaceContainer, class_students);
//                transaction.commit();
//            }
//        });
//
//
//        /* Edit Class */
//        binding.buttonEditClass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Add_Class add_class = new Add_Class();
////                FragmentTransaction transaction = getFragmentManager().beginTransaction();
////                transaction.replace(R.id.replaceContainer, add_class);
////                transaction.commit();
//            }
//        });
//
//        /* Delete Class */
//        binding.buttonDeleteClass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Add_Class add_class = new Add_Class();
////                FragmentTransaction transaction = getFragmentManager().beginTransaction();
////                transaction.replace(R.id.replaceContainer, add_class);
////                transaction.commit();
//            }
      //  });




        return binding.getRoot();
    }
}