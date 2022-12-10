package com.example.ee193take2.ui.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.junit.Test;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Dao
public interface DAO {

    /**------------------------------student methods----------------------------------------------**/

    //list all student
    @Query("SELECT * FROM student_table")
    LiveData<List<Student>> getStudent();

    //insert one student per time
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertStudent(Student student);

    //clear the table
    @Query("DELETE FROM student_table")
    void deleteAllStudents();

    @Update
    void updateStudent(Student student);

    @Delete
    void deleteStudent(Student student);

    //search student by the student name
    @Query("SELECT * FROM student_table where last_name = :last_name AND first_name = :first_name")
    LiveData<List<Student>> getByName(String first_name, String last_name);

    //Search student by the student id
    @Query("SELECT * FROM student_table where student_id = :studentID")
    LiveData<Student> getByID(int studentID);

    /**------------------------------course methods----------------------------------------------**/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCourse(Course course);

    @Update
    void updateCourse(Course course);

    @Delete
    void deleteCourse(Course course);

    //get all courses
    @Query("SELECT * FROM course_table")
    LiveData<List<Course>> getAlphabetizedCourses();

    //search the course through its id
    @Query("SELECT * FROM course_table WHERE course_id = :courseID")
    LiveData<Course> getCourseByID(int courseID);

    //delete all course
    @Query("DELETE FROM course_table")
    void deleteAllCourse();

    /**-------------------------Course offering methods------------------------------------------**/
    //display all courseOffering
    @Query("SELECT * FROM courseOffering_table")
    LiveData<List<CourseOffering>> getCourseOffering();

    //display CourseOffering by courseID
    @Query("SELECT * FROM courseOffering_table WHERE cid=:courseID")
    LiveData<List<CourseOffering>> getCourseOfferingByCourseID(int courseID);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertClassOffering(CourseOffering classOffering);

    @Update
    void updateClassOffering(CourseOffering classOffering);

    @Delete
    void deleteClassOffering(CourseOffering classOffering);

    /**-------------------------Student and ClassOffering methods----------------------------------**/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertStudentAndClassOffering(StudentClassOffering studentClassOffering);

    @Delete
    void deleteStudentAndClassOffering(StudentClassOffering studentClassOffering);

    //display student information by classOffering ID
    @Query("SELECT * FROM student_table as s INNER JOIN studentclassoffering_table as sc " +
            "ON s.student_id = sc.student_id iNNER JOIN courseOffering_table as c ON " +
            "sc.courseOffering_id = c.cid WHERE c.cid= :courseOfferingID")
    LiveData<List<Student>> getStudentByClassOfferingID(int courseOfferingID);

    //Display a student's Courseoffering list
    @Query("SELECT * FROM courseOffering_table as c INNER JOIN studentclassoffering_table as sc " +
            "ON c.cid = sc.courseOffering_id iNNER JOIN student_table as s ON " +
            "s.student_id = sc.student_id WHERE s.student_id = :id")
    LiveData<List<CourseOffering>> getClassOfferingByStudentID(int id);

    /**------------------------------Calendar Table------------------------------------------------**/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCalendar(CalendarCourseOffering calendar);

    @Delete
    void deleteCalendar(CalendarCourseOffering calendar);

    //display course offering's calendar by course offering ID
    @Query("SELECT date FROM calendar_table as ct INNER JOIN courseOffering_table as co " +
            "ON ct.courseOffering_id = co.cid WHERE co.cid = :courseOfferingID ORDER BY date DESC")
    LiveData<List<Date>> getCalendarByCourseOfferingID(int courseOfferingID);

    //display the list of course offering by the date
    @Query("SELECT * FROM courseOffering_table as co INNER JOIN calendar_table as ct ON " +
            "co.cid = ct.courseOffering_id WHERE ct.date = :date")
    LiveData<List<CourseOffering>> getCourseOfferingByDate(Date date);

    /**-------------------------------Attendance Table----------------------------------------------**/

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAttendance(Attendance attendance);

    @Delete
    void deleteAttendance(Attendance attendance);

    @Update
    void updateAttendance(Attendance attendance);

    //get a student's attendance on a specific date and course offering id
    @Query("SELECT attend FROM attendance_table WHERE student_id = :student_id " +
            "AND date = :date AND courseOffering_id = :courseOffering_id")
    LiveData<Boolean> getAllAttendanceByStudentIDDateCourseOffering(Date date, int student_id, int courseOffering_id);

    //from date start to date end, for a specific course offering id, get that day's all student attendance,
    // the number of result represents the number of presence that day
    @Query("SELECT COUNT(attend) FROM attendance_table WHERE date >= :start AND date <= :end " +
            "AND courseOffering_id = :courseOffering_id")
    LiveData<List<Integer>> getAttendPerformanceByTime(Date start, Date end, int courseOffering_id);

    /**--------------------------------User-------------------------------------------------------**/
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void insertUser(User user);
//
//    @Update
//    void updateUser(User user);
//
//    @Delete
//    void deleteUser(User user);
//
//    //get the user's password, can use this to check if the password is correct or not
//    @Query("SELECT password FROM user WHERE user.Name = :name")
//    String getPassword(String name);
//
//    //get the user's information
//    @Query("SELECT * FROM user WHERE user.Name = :name")
//    User getUser(String name);





}