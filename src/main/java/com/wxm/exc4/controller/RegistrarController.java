package com.wxm.exc4.controller;

import com.wxm.exc4.annotation.AdminLoginRequire;
import com.wxm.exc4.entity.College;
import com.wxm.exc4.entity.Professor;
import com.wxm.exc4.entity.Registrar;
import com.wxm.exc4.entity.Student;
import com.wxm.exc4.service.CollegeService;
import com.wxm.exc4.service.CourseService;
import com.wxm.exc4.service.ProfessorService;
import com.wxm.exc4.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
        System.out.println(colleges);
        return "registrar";
    }
}
