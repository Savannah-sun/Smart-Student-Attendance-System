package com.example.ee193take2.ui.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Student.class, Course.class, CourseOffering.class, StudentClassOffering.class, CalendarCourseOffering.class, Attendance.class},version = 1)
@TypeConverters({Converters.class})
public abstract class DAOdatabase extends RoomDatabase {
    public abstract DAO allDao();
    private static DAOdatabase INSTANCE;

    public static DAOdatabase getInstance(Context context){
        if (INSTANCE == null) {
            synchronized (StudentRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = create(context);
                }
            }
        }
        return INSTANCE;
    }

    private static DAOdatabase create(Context context) {
        return Room.databaseBuilder( context,DAOdatabase.class,"database").allowMainThreadQueries().build();
    }
}
