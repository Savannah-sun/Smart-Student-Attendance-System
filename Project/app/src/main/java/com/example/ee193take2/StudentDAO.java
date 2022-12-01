package com.example.ee193take2;
import android.provider.UserDictionary;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

import com.example.ee193take2.Student;

@Dao
public interface StudentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Student student);

    @Query("DELETE FROM student_table")
    void deleteAll();

    @Query("SELECT * FROM student_table ORDER BY last_name ASC")
    LiveData<List<Student>> getAlphabetizedStudents();
}
