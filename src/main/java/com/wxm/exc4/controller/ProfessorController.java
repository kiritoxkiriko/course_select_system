package com.wxm.exc4.controller;

import com.wxm.exc4.annotation.TeacherLoginRequire;
import com.wxm.exc4.entity.Course;
import com.wxm.exc4.entity.Professor;
import com.wxm.exc4.entity.Student;
import com.wxm.exc4.service.CourseService;
import com.wxm.exc4.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
            model.addAttribute("course",course);
            model.addAttribute("students",students);
            return "student_list";
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
            return "teacher";
        }
    }
}
