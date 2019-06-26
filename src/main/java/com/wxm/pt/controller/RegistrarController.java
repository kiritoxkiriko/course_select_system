package com.wxm.pt.controller;

import com.wxm.pt.annotation.AdminLoginRequire;
import com.wxm.pt.entity.*;
import com.wxm.pt.service.CollegeService;
import com.wxm.pt.service.CourseService;
import com.wxm.pt.service.ProfessorService;
import com.wxm.pt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/registrar")
public class RegistrarController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private CourseService courseService;

    @RequestMapping("")
    @AdminLoginRequire
    public String registrar(HttpSession session, Model model){
        Registrar registrar= (Registrar) session.getAttribute("user");
        model.addAttribute(registrar);
        List<Professor> professors=professorService.getAll();
        List<Student> students=studentService.getAll();
        List<College> colleges=collegeService.getAllColleges();
        model.addAttribute("colleges",colleges);
        model.addAttribute("students",students);
        model.addAttribute("professors",professors);
        model.addAttribute(registrar);
        return "admin/admin";
    }

    @AdminLoginRequire
    @RequestMapping("/courseList")
    public String courseList(HttpSession session,Model model){
        Registrar registrar= (Registrar) session.getAttribute("user");
        model.addAttribute("registrar",registrar);
        List<College> colleges=collegeService.getAllColleges();
        List<Course> courses=courseService.getAll();
        List<List<String>> weekStrsList=new ArrayList<>(20);
        for (Course c:
                courses){
            weekStrsList.add(courseService.getDaysOfWeekStrs(c));
        }
        model.addAttribute("weekStrsList",weekStrsList);
        model.addAttribute("colleges",colleges);
        model.addAttribute("courses",courses);
        model.addAttribute(registrar);
        return "admin/course_list";
    }

    @AdminLoginRequire
    @RequestMapping("/courseModify/{courseId}")
    public String courseModify(HttpSession session,Model model, @PathVariable long courseId){
        Registrar registrar= (Registrar) session.getAttribute("user");
        model.addAttribute("registrar",registrar);
        Course course=courseService.getCourseById(courseId);
        if(course==null){
            return "error/404";
        }
        model.addAttribute("course",course);
        model.addAttribute("weekStrs",CourseService.weeks);
        model.addAttribute("weekNums",courseService.getDaysOfWeekNums(course));
        return "admin/course_modify";
    }
}
