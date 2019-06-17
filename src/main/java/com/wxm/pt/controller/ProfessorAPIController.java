package com.wxm.pt.controller;

import com.wxm.pt.entity.Course;
import com.wxm.pt.entity.CourseInfo;
import com.wxm.pt.entity.Professor;
import com.wxm.pt.entity.Student;
import com.wxm.pt.service.CourseInfoService;
import com.wxm.pt.service.CourseService;
import com.wxm.pt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Alex Wang
 * @date 2019/06/17
 */
@RestController
@RequestMapping("/api/courseInfo")
public class ProfessorAPIController {
    @Autowired
    private CourseInfoService courseInfoService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;

    @RequestMapping("/add")
    public boolean addScore(HttpSession session, @RequestParam("student_id") long studentId,@RequestParam("course_id") long courseId, @RequestParam("score") double score){
        Professor professor=verify(session);
        Student student=studentService.getStudentById(studentId);
        if(professor==null){
            return false;
        }
        Course course=courseService.getCourseById(courseId);
        if(course==null||student==null){
            return false;
        }
        if(!course.getProfessor().equals(professor)){
            return false;
        }
        return courseInfoService.add(course,student,score)!=null;
    }

    @RequestMapping("/modify")
    public boolean modifyScore(HttpSession session, @RequestParam("student_id") long studentId,@RequestParam("course_id") long courseId, @RequestParam("score") double score){
        Professor professor=verify(session);
        Student student=studentService.getStudentById(studentId);
        if(professor==null){
            return false;
        }
        Course course=courseService.getCourseById(courseId);
        if(course==null||student==null){
            return false;
        }
        if(!course.getProfessor().equals(professor)){
            return false;
        }

        return courseInfoService.modify(course,student,score)!=null;
    }
    private Professor verify(HttpSession session){
        Object o=session.getAttribute("user");
        if(!(o instanceof Professor)){
            return null;
        }
        return (Professor) o;
    }
}
