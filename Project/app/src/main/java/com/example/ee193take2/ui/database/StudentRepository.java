package com.example.ee193take2.ui.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentRepository {

    private DAO mStudentDao;
    private LiveData<List<Student>> mAllStudents;


    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public StudentRepository(Application application) {
        StudentRoomDatabase db = StudentRoomDatabase.getDatabase(application);
        mStudentDao = db.CourseWithStudentDAO();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Student>> getAllStudents() {
        return mAllStudents;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insertStudent(Student student) {
        StudentRoomDatabase.databaseWriteExecutor.execute(() -> {
            mStudentDao.insertStudent(student);
        });
    }

    public void deleteAll(){
        StudentRoomDatabase.databaseWriteExecutor.execute(() -> {
            mStudentDao.deleteAll();
        });
    }
//    //Returns future which must be "GET"
//    public Future<List<String>> getClasses(int id){
//         return (Future<List<String>>) StudentRoomDatabase.databaseWriteExecutor.submit(() -> {
//             mStudentDao.getClasses(id);
//         });
//    }

//    //Sets classes.
//    public void updateClasses(List<String> classes, int id){
//        StudentRoomDatabase.databaseWriteExecutor.execute(() -> {
//            mStudentDao.updateClasses(classes, id);
//        });
//    }




}
