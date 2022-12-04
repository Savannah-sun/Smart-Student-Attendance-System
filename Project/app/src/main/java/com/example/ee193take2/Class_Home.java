package com.example.ee193take2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ee193take2.databinding.FragmentClassHomeBinding;




public class Class_Home extends Fragment {

    FragmentClassHomeBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentClassHomeBinding.inflate(inflater, container, false);

        return inflater.inflate(R.layout.fragment_class__home, container, false);
    }
}