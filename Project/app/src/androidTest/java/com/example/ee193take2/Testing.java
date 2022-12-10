package com.example.ee193take2;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.ee193take2.ui.database.Attendance;
import com.example.ee193take2.ui.database.CalendarCourseOffering;
import com.example.ee193take2.ui.database.Course;
import com.example.ee193take2.ui.database.CourseOffering;
import com.example.ee193take2.ui.database.DAO;
import com.example.ee193take2.ui.database.DAOdatabase;
import com.example.ee193take2.ui.database.Student;
import com.example.ee193take2.ui.database.StudentClassOffering;

import org.junit.*;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

@RunWith(AndroidJUnit4.class)
public class Testing {
    private DAO dao;
    private DAOdatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = DAOdatabase.getInstance(context);
        dao = db.allDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        for(int i = 0;i<100;i++){
            Student s = new Student(i,"a","b","bb bb","freshman","card");
            db.allDao().insertStudent(s);
        }
    }

    @Test
    public void testSearchByIdAndLastName(){
        System.out.println(dao.getByID(1));
//        Student s = new Student(100,"first","last","bb bb","freshman","card"    );
//        dao.insertStudent(s);
        for(Student x : dao.getByName("f","l")){
            System.out.println(x);
        }
    }

    @Test
    public void testUpdateAndDelete(){
        Student s = new Student(100,"f","l","bb bb","freshman","card"    );
        dao.updateStudent(s);
        Student s1 = new Student(0,"a","a","bb bb", "freshman","card");
        dao.deleteStudent(s1);
    }

    @Test
    public void testCourseTable(){
        for(int i = 1000; i<1100;i++){
            Course c = new Course(i,"helloWorld",true,2);
            dao.insertCourse(c);
        }

    }

    @Test
    public void testUpdateAndDeleteCourse(){
        Course c = new Course(1000,"hi",true,3);
        dao.updateCourse(c);
        Course c1 = new Course(1001,"helloWorld",true,3);
        dao.deleteCourse(c1);
    }

    @Test
    public void testClassOfferingTable(){
        for(int i = 1002; i <1100;i++){
            CourseOffering c = new CourseOffering(i+1000,i,"Halligen",22);
            dao.insertClassOffering(c);
        }
    }

    @Test
    public void testSearchUpdateDeleteClassOffering(){
        CourseOffering c = new CourseOffering(2002,1002,"JCC",22);
        dao.updateClassOffering(c);
        for(CourseOffering x : dao.getCourseOfferingByCourseID(2002)){
            System.out.println(x);
        }
        dao.deleteClassOffering(new CourseOffering(2003,1003,"Halligen",22));
    }

    @Test
    public void testStudentAndClassOffering(){
        for(int i=20;i<40;i++){
            StudentClassOffering c = new StudentClassOffering(i,2030);
            dao.insertStudentAndClassOffering(c);
        }
        StudentClassOffering c = new StudentClassOffering(20,2031);
        dao.insertStudentAndClassOffering(c);
        for(CourseOffering s : dao.getClassOfferingByStudentID(20)){
            System.out.println(s);
        }
        for(Student s : dao.getStudentByClassOfferingID(2030)){
            System.out.println(s);
        }
    }

    @Test
    public void testdeleteStudentAndClassOffering(){
        dao.deleteStudentAndClassOffering(new StudentClassOffering(20,2031));
        for(CourseOffering s : dao.getClassOfferingByStudentID(20)){
            System.out.println(s);
        }
    }

    @Test
    public void testCalendarCourseOffering(){
        Date date = Date.valueOf("2022-12-9");
        dao.insertCalendar(new CalendarCourseOffering(date,2023));
        Date d1 = Date.valueOf("2022-12-10");
        dao.insertCalendar(new CalendarCourseOffering(d1,2023));
        for(Date d : dao.getCalendarByCourseOfferingID(2023)){
            System.out.println(d);
        }
        for(CourseOffering c : dao.getCourseOfferingByDate(d1)){
            System.out.println(c);
        }
    }

    @Test
    public void testUpdateDeleteCalendarCourseOffering(){
        Date date = Date.valueOf("2022-12-9");
        dao.deleteCalendar(new CalendarCourseOffering(date,2023));
        for(Date d : dao.getCalendarByCourseOfferingID(2023)){
            System.out.println(d);
        }
    }

    @Test
    public void testAttendance(){
        Date date = Date.valueOf("2022-12-9");
        dao.insertCalendar(new CalendarCourseOffering(date,2023));
        Date date1 = Date.valueOf("2022-12-11");
        dao.insertCalendar(new CalendarCourseOffering(date1,2030));
        for(int i = 50;i<70;i++) {
            dao.insertAttendance(new Attendance(i,2030,date1,true));
        }
        System.out.println(dao.getAllAttendanceByStudentIDDateCourseOffering(date1,51,2023));
        for(int b : dao.getAttendPerformanceByTime(date,date1,2030)){
            System.out.println(b);
        }
    }

}
