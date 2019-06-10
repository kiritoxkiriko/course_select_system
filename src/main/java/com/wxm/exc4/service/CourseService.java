package com.wxm.exc4.service;

import com.wxm.exc4.dao.*;
import com.wxm.exc4.entity.Course;
import com.wxm.exc4.entity.Professor;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex Wang
 * @date 2019/05/12
 */
@Service
public class CourseService {
    public static final int MAX_SELECT_NUM = 10;
    public static final int MIN_SELECT_NUM = 3;
    private final String[] weeks={"周一","周二","周三","周四","周五","周六","周日"};
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
    public Course getCourseById(long courseId){
        return courseDao.findById(courseId).orElse(null);
    }
    public List<Course> getCourseByProfessor(Professor professor){
        return courseDao.findCoursesByProfessor(professor);
    }
    public List<Course> getCourseCanOpenByProfessor(Professor professor){
        return courseDao.findCoursesByProfessorAndSelectNumGreaterThan(professor,MIN_SELECT_NUM-1);
    }
    public boolean isCollide(Course course, Course... courses){
        return this.isCollide(course,courses);
    }
    public boolean isCollide(Course course, Iterable<Course> courses){
        for (Course c:
                courses) {
            if(c.getBeginWeek()<=course.getBeginWeek()||c.getFinishWeek()>=course.getFinishWeek()){
                String[] strs1=c.getDaysOfWeek().split(" ");
                String[] strs2=course.getDaysOfWeek().split(" ");
                //判断周数是否重合
                for (String s1:
                     strs1) {
                    for (String s2:
                        strs2){
                        if(strs1.equals(strs2)){
                            return true;
                        }
                    }
                }
                if(c.getDaysOfWeek()==course.getDaysOfWeek()){
                    if(c.getBeginTime()<=course.getBeginTime()||c.getFinishTime()<=course.getFinishTime()){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean isFull(Course course){
        return course.getSelectNum()>= course.getTotalNum();
    }
    public boolean canOpen(Course course){
        return course.getSelectNum()>= course.getTotalNum();
    }
    public List<String> getDaysOfWeekStrs(Course course){
        List<String> result=new ArrayList<>();
        String[] strs=course.getDaysOfWeek().split(" ");
        if (strs.length<=0){
        }else {
            for(String s:strs){
                int a=Integer.parseInt(s);
                result.add(weeks[a]);
            }
        }
        return result;
    }
}
