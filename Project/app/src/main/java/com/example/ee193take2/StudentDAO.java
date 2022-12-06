package com.example.ee193take2;
import android.provider.UserDictionary;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("SELECT * FROM student_table where last_name = :last_name")
    LiveData<List<Student>> getByLastName(String last_name);

    //Option to update students classes using a list of classes
    @Query("UPDATE student_table SET classes = :classes where uid = :id")
    void updateClasses(List<String> classes, int id);
    //Retrieve classes given a students unique ID.
    @Query("SELECT classes FROM student_table where uid =:id")
    public List<String> getClasses(int id);
}
