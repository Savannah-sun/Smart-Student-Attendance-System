package com.example.ee193take2.ui.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DAO {

    /**------------------------------student methods----------------------------------------------**/

    //list all student
    @Query("SELECT * FROM student_table")
    List<Student> getStudent();

    //insert one student per time
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertStudent(Student student);

    //clear the table
    @Query("DELETE FROM student_table")
    void deleteAll();

    @Update
    void updateStudent(Student student);

    @Delete
    void deleteStudent(Student student);

    //search student by the student name
    @Query("SELECT * FROM student_table where last_name = :last_name AND first_name = :first_name")
    List<Student> getByName(String first_name, String last_name);

    //Search student by the student id
    @Query("SELECT * FROM student_table where student_id = :studentID")
    Student getByID(int studentID);

    /**------------------------------course methods----------------------------------------------**/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCourse(Course course);

    @Update
    void updateCourse(Course course);

    @Delete
    void deleteCourse(Course course);

    @Query("SELECT * FROM course_table ORDER BY class_name ASC")
    List<Course> getAlphabetizedCourses();

    @Query("SELECT * FROM course_table WHERE course_id = :courseID")
    List<Course> getCourseByID(int courseID);

    /**-------------------------Course offering methods------------------------------------------**/
    @Query("SELECT * FROM courseOffering_table")
    List<CourseOffering> getCourseOffering();

    @Query("SELECT * FROM courseOffering_table WHERE course_id=:courseID")
    List<CourseOffering> getCourseOfferingByCourseID(int courseID);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertClassOffering(CourseOffering classOffering);

    @Update
    void updateClassOffering(CourseOffering classOffering);

    @Delete
    void deleteClassOffering(CourseOffering classOffering);




}