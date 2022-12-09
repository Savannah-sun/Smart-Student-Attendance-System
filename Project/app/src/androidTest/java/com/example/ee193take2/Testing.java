package com.example.ee193take2;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.ee193take2.ui.database.Course;
import com.example.ee193take2.ui.database.CourseOffering;
import com.example.ee193take2.ui.database.DAO;
import com.example.ee193take2.ui.database.DAOdatabase;
import com.example.ee193take2.ui.database.Student;

import org.junit.*;
import org.junit.runner.RunWith;

import java.io.IOException;

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
        //db.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        for(int i = 0;i<100;i++){
            Student s = new Student(i,"a","b","bb bb","freshman","card");
            db.allDao().insertStudent(s);
        }
        for(Student s : dao.getStudent()){
            System.out.println(s);
        }
    }

    @Test
    public void testSearchByIdAndLastName(){
        System.out.println(dao.getByID(1));
        Student s = new Student(100,"first","last","bb bb","freshman","card"    );
        dao.insertStudent(s);
        for(Student x : dao.getByName("first","last")){
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
            CourseOffering c = new CourseOffering(i,"Halligen",22);
            dao.insertClassOffering(c);
        }
        for(CourseOffering c : dao.getCourseOffering()){
            System.out.println(c);
        }
    }

}
