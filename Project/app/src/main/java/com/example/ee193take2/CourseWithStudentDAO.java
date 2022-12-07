package com.example.ee193take2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
interface CourseWithStudentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCourseWithStudent(CourseWithStudent join);

    @Transaction
    @Query("SELECT * FROM course_table")
    List<CourseStudentPair> getCourseStudentPair();


    @Transaction
    @Query("SELECT * FROM student_table")
    List<StudentCoursePair> getStudentCoursePair();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertStudent(Student student);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCourse(Course course);

    @Query("DELETE FROM student_table")
    void deleteAll();

    @Query("SELECT * FROM student_table ORDER BY last_name ASC")
    LiveData<List<Student>> getAlphabetizedStudents();

    @Query("SELECT * FROM student_table where last_name = :last_name")
    LiveData<List<Student>> getByLastName(String last_name);

    @Query("SELECT * FROM course_table ORDER BY class_name ASC")
    LiveData<List<Course>> getAlphabetizedCourses();

}