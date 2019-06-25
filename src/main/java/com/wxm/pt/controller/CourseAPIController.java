package com.wxm.pt.controller;

import com.wxm.pt.entity.College;
import com.wxm.pt.entity.Course;
import com.wxm.pt.entity.Professor;
import com.wxm.pt.entity.Registrar;
import com.wxm.pt.service.CollegeService;
import com.wxm.pt.service.CourseService;
import com.wxm.pt.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Alex Wang
 * @date 2019/06/25
 */
@RestController
@RequestMapping("/api/course")
public class CourseAPIController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private CollegeService collegeService;

    @RequestMapping("/add")
    public boolean add(HttpSession session,@RequestParam(required = false) Long professorId,@RequestParam String name,@RequestParam int beginWeek,@RequestParam int finishWeek,@RequestParam int beginTime,@RequestParam int finishTime,@RequestParam int totalNum,@RequestParam String daysOfWeek,@RequestParam double score){
        Professor professor=null;
        if(professorId==null){
            professor= verifyProfessor(session);
        }else {
            if(verifyRegistrar(session)==null){
                System.out.println("!register");
                return false;
            }
            professor=professorService.getProfessorById(professorId);
        }
        if(professor==null){
            System.out.println("!profess");
            return false;
        }
        College college=professor.getCollege();
        if(college==null){
            System.out.println("courseReAdd");
            return false;
        }
        return courseService.addCourse(college,professor,name,score,totalNum,beginWeek,finishWeek,daysOfWeek,beginTime,finishTime)!=null;
    }

    @RequestMapping("/modify")
    public boolean modify(HttpSession session,@RequestParam long courseId,@RequestParam(required = false) Integer beginWeek,@RequestParam(required = false) Integer finishWeek,@RequestParam(required = false) Integer beginTime,@RequestParam(required = false) Integer finishTime,@RequestParam(required = false) Integer totalNum,@RequestParam(required = false) String daysOfWeek,@RequestParam(required = false) Double score,@RequestParam(required = false) String location){
        Course course=courseService.getCourseById(courseId);
        if (course == null) {
            return false;
        }
        Object user=session.getAttribute("user");
        if(user instanceof Registrar){
        }else if(user instanceof Professor){
            Professor professor= (Professor) user;
            if(course.getProfessor()!=professor){
                return false;
            }
        }else {
            return false;
        }
        if(beginTime!=null){
            course.setBeginTime(beginTime);
        }
        if(finishTime!=null){
            course.setFinishTime(finishTime);
        }
        if(beginWeek!=null){
            course.setBeginWeek(beginWeek);
        }
        if(finishWeek!=null){
            course.setFinishWeek(finishWeek);
        }
        if(score!=null){
            course.setScore(score);
        }
        if(location!=null){
            course.setLocation(location);
        }
        if(daysOfWeek!=null){
            course.setDaysOfWeek(daysOfWeek);
        }
        return courseService.modifyCourse(course)!=null;
    }

    @RequestMapping("/remove")
    private boolean remove(HttpSession session,@RequestParam long courseId){
        Course course=courseService.getCourseById(courseId);
        if (course == null) {
            System.out.println("!ext");
            return false;
        }
        Object user=session.getAttribute("user");
        if(user instanceof Registrar){
        }else if(user instanceof Professor){
            Professor professor= (Professor) user;
            if(!course.getProfessor().equals(professor)){
                System.out.println("!professor");
                return false;
            }
        }else {
            System.out.println("nosession");
            return false;
        }
        return courseService.deleteCourse(course);
    }

    private Professor verifyProfessor(HttpSession session){
        Object o=session.getAttribute("user");
        if(!(o instanceof Professor)){
            return null;
        }
        return (Professor) o;
    }

    private Registrar verifyRegistrar(HttpSession session){
        Object o=session.getAttribute("user");
        if(!(o instanceof Registrar)){
            return null;
        }
        return (Registrar) o;
    }

}
