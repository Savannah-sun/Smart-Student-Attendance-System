package com.example.ee193take2.ui.student;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ee193take2.ui.database.Student;
import com.example.ee193take2.ui.database.StudentRepository;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private StudentRepository mRepository;

    private final LiveData<List<Student>> mAllStudents;

    public StudentViewModel(Application application) {
        super(application);
        mRepository = new StudentRepository(application);
        mAllStudents = mRepository.getAllStudents();
    }

    public LiveData<List<Student>> getAllStudents() {
        return mAllStudents;
    }

    public void insertStudent(Student student) {
        mRepository.insertStudent(student);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }


//    public List<String> getClasses(int id) {
//        Future<List<String>> sFutures = mRepository.getClasses(id);
//        List<String> classes;
//        try {
//            classes = sFutures.get();
//        } catch (ExecutionException | InterruptedException ex) {
//            return Collections.emptyList();
//        }
//
//        return classes;
//    }

}
