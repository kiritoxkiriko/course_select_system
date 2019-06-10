package com.wxm.exc4;

import com.wxm.exc4.dao.*;
import com.wxm.exc4.entity.*;
import com.wxm.exc4.service.CourseService;
import com.wxm.exc4.service.ProfessorService;
import com.wxm.exc4.service.StudentService;
import com.wxm.exc4.service.StudyProgramService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Exc4ApplicationTests {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private CollegeDao collegeDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CourseOfferingDao courseOfferingDao;
    @Autowired
    private ProfessorDao professorDao;
    @Autowired
    private RegistrarDao registrarDao;
    @Autowired
    private StudyProgramDao studyProgramDao;
    @Autowired
    private DegreeDao degreeDao;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private StudyProgramService studyProgramService;
    @Autowired
    private CourseService courseService;
    @Test
    public void contextLoads() {
        College college=new College("软件");
        college=collegeDao.saveAndFlush(college);
        Degree degree=new Degree("大一",college);
        degree=degreeDao.save(degree);
        List<Student> students=new ArrayList<>();
        students.add(studentService.add(111,"王刚","123456",college));
        students.add(studentService.add(222,"李强","123456",college));
        students.add(studentService.add(333,"刘建华","123456",college));
        students.add(studentService.add(444,"赵四","123456",college));
        Professor professor=professorService.add(111,"滑稽","123456",college);
        //
        List<Course> courses=new ArrayList<>();
        courses.add(new Course(college,professor,"高等数学",6,null,10,10,1,16,"1 3",1,2));
        courses.add(new Course(college,professor,"线性代数",6,null,10,10,1,16,"1 3",1,2));
        courses.add(new Course(college,professor,"java基础",6,null,10,10,1,16,"1 3",5,6));
        courses.add(new Course(college,professor,"python基础",6,null,10,10,3,10,"2 4",3,4));
        courses.add(new Course(college,professor,"数据结构",6,null,10,10,5,5,"3 5",3,4));
        courses.add(new Course(college,professor,"c语言",6,null,10,10,1,16,"1 3",7,8));
        courses.add(new Course(college,professor,"数据库",6,null,10,10,1,16,"1 3",3,4));
        courseDao.saveAll(courses);
        List<Course> courses1=new ArrayList<>();
        courses1.add(courseService.getCourseById(1));
        courses.add(new Course(college,professor,"数据库1",6,courses,10,10,1,16,"1 3",3,4));
        courseDao.saveAll(courses);
        //        courses=courseDao.findAll();
        //
        Time time=new Time(System.currentTimeMillis()+1000*60*10);//当前时间+10min
        CourseOffering courseOffering=new CourseOffering(degree,courses,time);
        courseOffering=courseOfferingDao.save(courseOffering);
        //
        Student student1=students.get(0);
        System.out.println(student1);
//        Course tempC=courses.get(4);
//        tempC.setSelectNum(3);
//        courseDao.save(tempC);
//        for(Course c:courses){
//            c.setSelectNum(3);
//            courseDao.save(c);
//        }
//        StudyProgram studyProgram=studyProgramService.add(student1,courseOffering);
//        for(Course c:courses){
//            System.out.println("addP "+studyProgramService.addPrimaryCourse(studyProgram,c));
//        }
//        System.out.println("removeP "+studyProgramService.removePrimaryCourse(studyProgram,courses.get(0)));
//        System.out.println(courses.get(0));
//        System.out.println("addP "+studyProgramService.addPrimaryCourse(studyProgram,courses.get(0)));
//        System.out.println(courses.get(0));
//        //
//        System.out.println("addS "+studyProgramService.addSecondaryCourse(studyProgram,courses.get(4)));
//        studyProgram=studyProgramService.getByStudentAndCourseOffering(student1,courseOffering);
//        System.out.println(studyProgram);
        //
//        System.out.println("submit "+studyProgramService.sumbit(studyProgram));
//        System.out.println("check "+studyProgramService.check(studyProgram));
//        System.out.println(studyProgram);
    }

}
