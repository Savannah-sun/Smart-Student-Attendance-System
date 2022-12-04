package com.example.ee193take2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.ee193take2.Student;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Student.class},version = 1, exportSchema = false)
public abstract class StudentRoomDatabase extends RoomDatabase {
    public abstract StudentDAO studentDAO();

    private static volatile StudentRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static StudentRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StudentRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    StudentRoomDatabase.class, "word_database")
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
                StudentDAO dao = INSTANCE.studentDAO();
                dao.deleteAll();

                Student student = new Student("Ever", "Greatest");
                dao.insert(student);

                student = new Student("123", "Testing");
                dao.insert(student);
            });
        };
    };

}
