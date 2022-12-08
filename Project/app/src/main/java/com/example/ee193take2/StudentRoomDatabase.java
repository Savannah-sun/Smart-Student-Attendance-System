package com.example.ee193take2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.ee193take2.Student;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Student.class, Course.class, CourseWithStudent.class},version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class StudentRoomDatabase extends RoomDatabase {
    public abstract CourseWithStudentDAO CourseWithStudentDAO();

    private static volatile StudentRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static StudentRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StudentRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    StudentRoomDatabase.class, "student_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);

            //If you want to keep data through app restarts,
            //comment out the following block

            databaseWriteExecutor.execute(() -> {
                //Populate the database in the background
                //Start with some starter items.
                CourseWithStudentDAO dao = INSTANCE.CourseWithStudentDAO();
                dao.deleteAll();

                Student student = new Student("Ever", "Greatest");
                dao.insertStudent(student);

                student = new Student("123", "Testing");
                dao.insertStudent(student);
            });
        };
    };

}
