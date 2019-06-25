package com.wxm.pt.controller;

import com.wxm.pt.annotation.TeacherLoginRequire;
import com.wxm.pt.entity.*;
import com.wxm.pt.service.CollegeService;
import com.wxm.pt.service.CourseInfoService;
import com.wxm.pt.service.CourseService;
import com.wxm.pt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Alex Wang
 * @date 2019/05/14
 */
@Controller
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseInfoService courseInfoService;
    @Autowired
    private CollegeService collegeService;

    @TeacherLoginRequire
    @RequestMapping("")
    public String professor(Model model, HttpSession session, @RequestParam(value = "co_pos",required = false)Integer coPos){
        Professor professor= (Professor) session.getAttribute("user");
        List<Course> coursesCanOpen=courseService.getCourseCanOpenByProfessor(professor);
        List<Course> courses=courseService.getCourseByProfessor(professor);
        model.addAttribute("professor",professor);
        if(coPos!=null&&coPos<coursesCanOpen.size()&&coPos>=0){
            Course course=coursesCanOpen.get(coPos);
            List<Student> students=studentService.getStudentsBySelectCourseId(course.getId());
            Map<Student, CourseInfo> courseInfoMap=courseInfoService.getCourseInfoMapByCourse(course);
            model.addAttribute("course",course);
            model.addAttribute("students",students);
            model.addAttribute("courseInfoMap",courseInfoMap);
            return "teacher/student_list";
        }else{
            List<List<String>> weekStrsList=new ArrayList<>(20);
            for (Course c:
                    courses){
                weekStrsList.add(courseService.getDaysOfWeekStrs(c));
            }
            List<List<String>> canOpenWeekStrsList=new ArrayList<>(20);
            for (Course c:
                    coursesCanOpen){
                canOpenWeekStrsList.add(courseService.getDaysOfWeekStrs(c));
            }
            model.addAttribute("weekStrsList",weekStrsList);
            model.addAttribute("canOpenWeekStrsList",canOpenWeekStrsList);
            model.addAttribute("courses",courses);
            model.addAttribute("coursesCanOpen",coursesCanOpen);
            return "teacher/teacher";
        }
    }
    @TeacherLoginRequire
    @RequestMapping("/add")
    public String add(HttpSession session,Model model){
        Professor professor= (Professor) session.getAttribute("user");
        model.addAttribute("professor",professor);
        List<College> colleges=collegeService.getAllColleges();
        model.addAttribute("colleges",colleges);
        model.addAttribute("weekStrs",CourseService.weeks);
        return "teacher/add_course";
    }
}
