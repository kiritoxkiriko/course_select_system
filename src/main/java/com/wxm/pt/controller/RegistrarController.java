package com.wxm.pt.controller;

import com.wxm.pt.annotation.AdminLoginRequire;
import com.wxm.pt.entity.College;
import com.wxm.pt.entity.Professor;
import com.wxm.pt.entity.Registrar;
import com.wxm.pt.entity.Student;
import com.wxm.pt.service.CollegeService;
import com.wxm.pt.service.ProfessorService;
import com.wxm.pt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "admin/admin";
    }
}
