package com.example.ee193take2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentRepository {

    private StudentDAO mStudentDao;
    private LiveData<List<Student>> mAllStudents;


    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public StudentRepository(Application application) {
        StudentRoomDatabase db = StudentRoomDatabase.getDatabase(application);
        mStudentDao = db.studentDAO();
        mAllStudents = mStudentDao.getAlphabetizedStudents();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Student>> getAllStudents() {
        return mAllStudents;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Student student) {
        StudentRoomDatabase.databaseWriteExecutor.execute(() -> {
            mStudentDao.insert(student);
        });
    }
}

