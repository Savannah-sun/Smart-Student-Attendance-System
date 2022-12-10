package com.example.ee193take2.ui.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Student.class, Course.class, CourseOffering.class, StudentClassOffering.class, CalendarCourseOffering.class, Attendance.class},version = 1)
@TypeConverters({Converters.class})
public abstract class DAOdatabase extends RoomDatabase {
    public abstract DAO DAO();


    private static volatile DAOdatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static DAOdatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DAOdatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DAOdatabase.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sDAODatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            DAO dao = INSTANCE.DAO();
            dao.deleteAllStudents();

        };
    };
}
