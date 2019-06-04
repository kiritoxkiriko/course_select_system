package com.wxm.exc4.controller;

import com.wxm.exc4.annotation.AutoJump;
import com.wxm.exc4.annotation.StudentLoginRequire;
import com.wxm.exc4.entity.*;
import com.wxm.exc4.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex Wang
 * @date 2019/05/14
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudyProgramService studyProgramService;
    @Autowired
    private CourseOfferingService courseOfferingService;
    @Autowired
    CourseService courseService;
//    @Autowired
//    CollegeService collegeService;
    @StudentLoginRequire
    @RequestMapping("")
    public String student(Model model, HttpSession session, @RequestParam(value = "co_pos",required = false)Integer coPos){
        Student student= (Student) session.getAttribute("user");
        model.addAttribute(student);
        College college=student.getCollege();
        List<CourseOffering> courseOfferings=courseOfferingService.getCourseOfferingsByCollege(college);
        if(coPos!=null&&coPos<courseOfferings.size()&&coPos>=0){
            CourseOffering courseOffering=courseOfferings.get(coPos);
            StudyProgram studyProgram=studyProgramService.add(student,courseOffering);
            List<List<String>> weekStrsList=new ArrayList<>(20);
            for (Course c:
                courseOffering.getCourses()){
                weekStrsList.add(courseService.getDaysOfWeekStrs(c));
            }
            model.addAttribute(courseOffering);
            model.addAttribute("weekStrsList",weekStrsList);
            model.addAttribute(studyProgram);
            return "course_select";
        }else{
            model.addAttribute(courseOfferings);
            return "student";
        }
    }
}
