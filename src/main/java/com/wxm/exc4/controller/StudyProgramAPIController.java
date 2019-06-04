package com.wxm.exc4.controller;

import com.wxm.exc4.entity.Course;
import com.wxm.exc4.entity.CourseOffering;
import com.wxm.exc4.entity.Student;
import com.wxm.exc4.entity.StudyProgram;
import com.wxm.exc4.service.CourseOfferingService;
import com.wxm.exc4.service.CourseService;
import com.wxm.exc4.service.StudyProgramService;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Alex Wang
 * @date 2019/05/14
 */
@RestController
@RequestMapping("/api/studyProgram")
public class StudyProgramAPIController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudyProgramService studyProgramService;
    @Autowired
    private CourseOfferingService courseOfferingService;
    @RequestMapping("addPrimaryCourse")
    public boolean addPrimaryCourse(HttpSession session, @RequestParam("course_id")long courseId,@RequestParam("study_program_id")long studyProgramId){
        Object o=session.getAttribute("user");
        if(!(o instanceof Student)){
            return false;
        }
        Student student= (Student) o;
        Course course=courseService.getCourseById(courseId);
        if(course==null){
            return false;
        }
        StudyProgram studyProgram=studyProgramService.getStudeyProgramById(studyProgramId);
        if(studyProgram==null){
            return false;
        }
        if(!studyProgram.getStudent().equals(student)){
            return false;
        }
        return studyProgramService.addPrimaryCourse(studyProgram,course);
    }
    @RequestMapping("addSecondaryCourse")
    public boolean addSecondaryCourse(HttpSession session, @RequestParam("course_id")long courseId,@RequestParam("study_program_id")long studyProgramId){
        Object o=session.getAttribute("user");
        if(!(o instanceof Student)){
            return false;
        }
        Student student= (Student) o;
        Course course=courseService.getCourseById(courseId);
        if(course==null){
            return false;
        }
        StudyProgram studyProgram=studyProgramService.getStudeyProgramById(studyProgramId);
        if(studyProgram==null){
            return false;
        }
        if(!studyProgram.getStudent().equals(student)){
            return false;
        }
        return studyProgramService.addSecondaryCourse(studyProgram,course);
    }
    @RequestMapping("removePrimaryCourse")
    public boolean removePrimaryCourse(HttpSession session, @RequestParam("course_id")long courseId,@RequestParam("study_program_id")long studyProgramId){
        Object o=session.getAttribute("user");
        if(!(o instanceof Student)){
            return false;
        }
        Student student= (Student) o;
        Course course=courseService.getCourseById(courseId);
        if(course==null){
            return false;
        }
        StudyProgram studyProgram=studyProgramService.getStudeyProgramById(studyProgramId);
        if(studyProgram==null){
            return false;
        }
        if(!studyProgram.getStudent().equals(student)){
            return false;
        }
        return studyProgramService.removePrimaryCourse(studyProgram,course);
    }
    @RequestMapping("removeSecondaryCourse")
    public boolean removeSecondaryPrimaryCourse(HttpSession session,@RequestParam("course_id")long courseId,@RequestParam("study_program_id")long studyProgramId){
        Object o=session.getAttribute("user");
        if(!(o instanceof Student)){
            return false;
        }
        Student student= (Student) o;
        Course course=courseService.getCourseById(courseId);
        if(course==null){
            return false;
        }
        StudyProgram studyProgram=studyProgramService.getStudeyProgramById(studyProgramId);
        if(studyProgram==null){
            return false;
        }
        if(!studyProgram.getStudent().equals(student)){
            return false;
        }
        return studyProgramService.removeSecondaryCourse(studyProgram,course);
    }
    @RequestMapping("submit")
    public boolean submit(HttpSession session,@RequestParam("study_program_id")long studyProgramId){
        Object o=session.getAttribute("user");
        if(!(o instanceof Student)){
            return false;
        }
        Student student= (Student) o;
        StudyProgram studyProgram=studyProgramService.getStudeyProgramById(studyProgramId);
        if(studyProgram==null){
            return false;
        }
        if(!studyProgram.getStudent().equals(student)){
            return false;
        }
        return studyProgramService.sumbit(studyProgram);
    }
}
