package com.example.ee193take2.ui.database;

import android.app.Application;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.sql.Date;
import java.util.List;

public class DBViewModel extends AndroidViewModel {

    private DAO mDAO;
    DAOdatabase db;

    public DBViewModel(Application application) {
        super(application);
        db = DAOdatabase.getDatabase(application);
        mDAO = db.DAO();
        mAllStudents = mDAO.getStudent();
        mAllCourses = mDAO.getAlphabetizedCourses();
    }
//    @WorkerThread
//    public void ClearData(){
//        db.clearAllTables();
//    }
/**-------------------------------Student View Model ----------------------------------------------**/

    public LiveData<List<Student>> getAllStudents() {
        return mDAO.getStudent();
    }

    private LiveData<List<Student>> mAllStudents;


    public void insertStudent(Student student) {
        DAOdatabase.databaseWriteExecutor.execute(()->{
            mDAO.insertStudent(student);
        });
    }

    public void deleteAllStudents() {
        DAOdatabase.databaseWriteExecutor.execute(()->{
            mDAO.deleteAllStudents();
        });
    }

    void updateStudent(Student student){
        DAOdatabase.databaseWriteExecutor.execute(()->{
            mDAO.updateStudent(student);
        });
    }

    void deleteStudent(Student student) {
        DAOdatabase.databaseWriteExecutor.execute(()->{
            mDAO.deleteStudent(student);
        });
    }

    //search student by the student name
    LiveData<List<Student>> getByName(String first_name, String last_name) {return mDAO.getByName(first_name,last_name);};

    //Search student by the student id
    LiveData<Student> getByID(int studentID) {return mDAO.getByID(studentID);};


/**-------------------------------Course View Model ----------------------------------------------**/

private LiveData<List<Course>> mAllCourses;

    public void insertCourse(Course course) {
        DAOdatabase.databaseWriteExecutor.execute(()->{
            mDAO.insertCourse(course);
        });
    }

    public void updateCourse(Course course) {
        DAOdatabase.databaseWriteExecutor.execute(()->{
            mDAO.updateCourse(course);
        });
    }

    public void deleteCourse(Course course) {
        DAOdatabase.databaseWriteExecutor.execute(()->{
            mDAO.deleteCourse(course);
        });
    }

    public LiveData<List<Course>> getAlphabetizedCourses() {
        return mDAO.getAlphabetizedCourses();
    }

    public LiveData<Course> getCourseByID(int courseID){
        return mDAO.getCourseByID(courseID);
    }

    public void deleteAllCourse() {
        DAOdatabase.databaseWriteExecutor.execute(() -> {
            mDAO.deleteAllCourse();
        });
    }

    public LiveData<Course> getCourseByName(String course_name){
        return mDAO.getCourseByName(course_name);
    }

    /**-------------------------------Course Offering View Model ----------------------------------------------**/


    public LiveData<List<CourseOffering>> getCourseOffering(){return mDAO.getCourseOffering();}


    public LiveData<List<CourseOffering>> getCourseOfferingByCourseID(int courseID){
        return mDAO.getCourseOfferingByCourseID(courseID);
    }


    public void insertClassOffering(CourseOffering classOffering){
            DAOdatabase.databaseWriteExecutor.execute(() -> {
            mDAO.insertClassOffering(classOffering);
            });
    };


    void updateClassOffering(CourseOffering classOffering){
        DAOdatabase.databaseWriteExecutor.execute(() -> {
            mDAO.updateClassOffering(classOffering);
        });
    };

    public void deleteClassOffering(CourseOffering classOffering){
        DAOdatabase.databaseWriteExecutor.execute(()-> {
            mDAO.deleteClassOffering(classOffering);
        });
    };


/**-------------------------------Student and ClassOffering View Model ----------------------------------------------**/

    public void insertStudentAndClassOffering(StudentClassOffering studentClassOffering) {
        DAOdatabase.databaseWriteExecutor.execute(()->{
            mDAO.insertStudentAndClassOffering(studentClassOffering);
        });
    }

   public void deleteStudentAndClassOffering(StudentClassOffering studentClassOffering){
        DAOdatabase.databaseWriteExecutor.execute(()->{
            mDAO.insertStudentAndClassOffering(studentClassOffering);
        });
    }

    public LiveData<List<Student>> getStudentByClassOfferingID(int courseOfferingID) {
        return mDAO.getStudentByClassOfferingID(courseOfferingID);};

    LiveData<List<CourseOffering>> getClassOfferingByStudentID(int id){
        return mDAO.getClassOfferingByStudentID(id);
    };


/**------------------------------Calendar View Model------------------------------------------------**/

    void insertCalendar(CalendarCourseOffering calendar){
        DAOdatabase.databaseWriteExecutor.execute(()-> {
            mDAO.insertCalendar(calendar);
        });
    };


    void deleteCalendar(CalendarCourseOffering calendar){
        DAOdatabase.databaseWriteExecutor.execute(()->{
            mDAO.deleteCalendar(calendar);
        });
    };


    LiveData<List<Date>> getCalendarByCourseOfferingID(int courseOfferingID){
        return mDAO.getCalendarByCourseOfferingID(courseOfferingID);
    }

    LiveData<List<CourseOffering>> getCourseOfferingByDate(Date date){
        return mDAO.getCourseOfferingByDate(date);
        };

/**------------------------------Attendance View Model------------------------------------------------**/

   public void insertAttendance(Attendance attendance){
        DAOdatabase.databaseWriteExecutor.execute(() -> {
            mDAO.insertAttendance(attendance);
        });
    };

    public void deleteAttendance(Attendance attendance) {
        DAOdatabase.databaseWriteExecutor.execute(() -> {
            mDAO.deleteAttendance(attendance);
        });
    };

    public void updateAttendance(Attendance attendance) {
        DAOdatabase.databaseWriteExecutor.execute(() -> {
            mDAO.updateAttendance(attendance);
        });
    };

    public LiveData<List<Boolean>> getAllAttendanceByStudentIDDateCourseOffering(int student_id, int courseOffering_id){
        return mDAO.getAllAttendanceByStudentIDDateCourseOffering(student_id, courseOffering_id);
    };

    public LiveData<List<Attendance>> getAttendanceByStudentID(int student_id, int courseOffering_id){
        return mDAO.getStudentAttendance(student_id,courseOffering_id);
    }

    public LiveData<List<Integer>> getAttendPerformanceByTime(Date start, Date end, int courseOffering_id){
        return mDAO.getAttendPerformanceByTime(start,end,courseOffering_id);
    };

    }