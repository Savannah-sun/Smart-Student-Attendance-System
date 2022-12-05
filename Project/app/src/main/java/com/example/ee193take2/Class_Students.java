package com.example.ee193take2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.ee193take2.databinding.FragmentClassStudentsBinding;

public class Class_Students extends Fragment {

    FragmentClassStudentsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentClassStudentsBinding.inflate(inflater, container, false);



        return binding.getRoot();
    }
}