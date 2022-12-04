package com.example.ee193take2.ui.student;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ee193take2.Student;
import com.example.ee193take2.StudentRepository;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private StudentRepository mRepository;

    private final LiveData<List<Student>> mAllStudents;

    public StudentViewModel (Application application){
        super(application);
        mRepository = new StudentRepository(application);
        mAllStudents = mRepository.getAllStudents();
    }

    public LiveData<List<Student>> getAllStudents() {return mAllStudents;}

    public void insert(Student student) {mRepository.insert(student);}

}
