package com.wxm.exc4.controller;

import com.wxm.exc4.entity.College;
import com.wxm.exc4.entity.Professor;
import com.wxm.exc4.entity.Registrar;
import com.wxm.exc4.entity.Student;
import com.wxm.exc4.service.CollegeService;
import com.wxm.exc4.service.ProfessorService;
import com.wxm.exc4.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Alex Wang
 * @date 2019/05/14
 */
@RestController
@RequestMapping("/api/")
public class RegistrarAPIController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private CollegeService collegeService;

    @RequestMapping(value = "student/add",method = RequestMethod.POST)
    public boolean addStudent(HttpSession session, @RequestParam("id")long id, @RequestParam("name")String name,@RequestParam("pwd")String pwd, @RequestParam("college_id")long collegeId){
        Registrar registrar=verify(session);
        if(registrar==null){
            return false;
        }
        College college=collegeService.getCollegeById(collegeId);
        if(college==null){
            return false;
        }
        return studentService.add(id,name,pwd,college)!=null;
    }
    @RequestMapping(value = "professor/add",method = RequestMethod.POST)
    public boolean addProfessor(HttpSession session, @RequestParam("id")long id, @RequestParam("name")String name,@RequestParam("pwd")String pwd, @RequestParam("college_id")long collegeId){
        Registrar registrar=verify(session);
        if(registrar==null){
            return false;
        }
        College college=collegeService.getCollegeById(collegeId);
        if(college==null){
            return false;
        }
        return professorService.add(id,name,pwd,college)!=null;
    }
    @RequestMapping("student/remove")
    public boolean removeStudent(HttpSession session,@RequestParam("id")long studentId){
        Registrar registrar=verify(session);
        if(registrar==null){
            return false;
        }
        return studentService.remove(studentId);
    }

    @RequestMapping("professor/remove")
    public boolean removeProfessor(HttpSession session,@RequestParam("id")long professorId){
        Registrar registrar=verify(session);
        if(registrar==null){
            return false;
        }
        return professorService.remove(professorId);
    }

    @RequestMapping("student/modify")
    public boolean modifyStudent(HttpSession session,@RequestParam("id")long id, @RequestParam("name")String name,@RequestParam("pwd")String pwd, @RequestParam("college_id")long collegeId){
        Registrar registrar=verify(session);
        if(registrar==null){
            return false;
        }
        College college=collegeService.getCollegeById(collegeId);
        if(college==null){
            return false;
        }
        Student student=new Student();
        student.setId(id);
        student.setName(name);
        student.setCollege(college);
        student.setPassword(pwd);
        return studentService.modify(student)!=null;
    }
    @RequestMapping("professor/modify")
    public boolean modifyProfessor(HttpSession session,@RequestParam("id")long id, @RequestParam("name")String name,@RequestParam("pwd")String pwd, @RequestParam("college_id")long collegeId){
        Registrar registrar=verify(session);
        if(registrar==null){
            return false;
        }
        College college=collegeService.getCollegeById(collegeId);
        if(college==null){
            return false;
        }
        Professor professor=new Professor();
        professor.setId(id);
        professor.setName(name);
        professor.setCollege(college);
        professor.setPassword(pwd);
        return professorService.modify(professor)!=null;
    }

    private Registrar verify(HttpSession session){
        Object o=session.getAttribute("user");
        if(!(o instanceof Registrar)){
            return null;
        }
        return (Registrar) o;
    }
}
