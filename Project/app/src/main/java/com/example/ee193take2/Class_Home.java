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


        /* Add Class */
        binding.addClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add_Class add_class = new Add_Class();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.replaceContainer, add_class);
                transaction.commit();
            }
        });


        /* See Class */
        binding.buttonPretendClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class_Info add_class = new Class_Info();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.replaceContainer, add_class);
                transaction.commit();
            }
        });



        return binding.getRoot();
    }
}