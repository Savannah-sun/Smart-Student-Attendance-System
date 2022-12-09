package com.example.ee193take2.ui.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class, Course.class, CourseOffering.class, StudentClassOffering.class},version = 1)
public abstract class DAOdatabase extends RoomDatabase {
    public abstract DAO allDao();
    private static DAOdatabase instance;

    public static DAOdatabase getInstance(Context context){
        if (instance == null){
            synchronized (DAOdatabase.class){
                if (instance == null){
                    instance = create(context);
                }
            }
        }
        return instance;
    }

    private static DAOdatabase create(Context context) {
        return Room.databaseBuilder( context,DAOdatabase.class,"database").allowMainThreadQueries().build();
    }
}
