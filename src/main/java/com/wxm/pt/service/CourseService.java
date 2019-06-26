package com.wxm.pt.service;

import com.wxm.pt.dao.*;
import com.wxm.pt.entity.College;
import com.wxm.pt.entity.Course;
import com.wxm.pt.entity.CourseOffering;
import com.wxm.pt.entity.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
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
    public static final String[] weeks={"周一","周二","周三","周四","周五","周六","周日"};
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
        String[] strs2=course.getDaysOfWeek().split(" ");
        for (Course c:
                courses) {
            if(!(c.getBeginWeek()>course.getFinishWeek()||c.getFinishWeek()<course.getBeginWeek())){
                String[] strs1=c.getDaysOfWeek().split(" ");
                //判断周数是否重合
                boolean flag=false;
                for (String s1:
                     strs1) {
                    for (String s2:
                        strs2){
                        if(s1.equals(s2)){
                            flag=true;
                            break;
                        }
                    }
                }
                if(flag){
                    if(!(c.getBeginTime()>course.getFinishTime()||c.getFinishTime()<course.getBeginTime())){
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
                result.add(weeks[a-1]);
            }
        }
        return result;
    }
    public List<Integer> getDaysOfWeekNums(Course course){
        List<Integer> result=new ArrayList<>();
        String[] strs=course.getDaysOfWeek().split(" ");
        if (strs.length<=0){
        }else {
            for(String s:strs){
                int a=Integer.parseInt(s);
                result.add(a);
            }
        }
        return result;
    }

    public Course addCourse(College college, Professor professor, String name, double score, int totalNum, int beginWeek, int finishWeek,String daysOfWeek, int beginTime,int finishTime,String location){
        if(courseDao.countByName(name)>0){
            return null;
        }
        Course course=new Course(college,professor,name,score,null,totalNum,0,beginWeek,finishWeek,daysOfWeek,beginTime,finishTime,location);
        List<CourseOffering> courseOfferings=courseOfferingDao.findAll();
        CourseOffering courseOffering=courseOfferings.get(0);
        if((course=courseDao.save(course))==null){
            return null;
        }
        courseOffering.getCourses().add(course);
        if(courseOfferingDao.save(courseOffering)==null){
            return null;
        }
        return course;
    }

    public Course modifyCourse(Course course){
        return courseDao.save(course);
    }

    public boolean deleteCourse(Course course){
        try {List<CourseOffering> courseOfferings=courseOfferingDao.findAll();
            CourseOffering courseOffering=courseOfferings.get(0);
            courseOffering.getCourses().remove(course);
            if(courseOfferingDao.save(courseOffering)==null){
                return false;
            }
            courseDao.delete(course);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Course> getAll(){
        return courseDao.findAll();
    }

}
