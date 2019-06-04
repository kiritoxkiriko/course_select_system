package com.wxm.exc4.service;

import com.wxm.exc4.dao.*;
import com.wxm.exc4.entity.Professor;
import com.wxm.exc4.entity.Registrar;
import com.wxm.exc4.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alex Wang
 * @date 2019/05/12
 */
@Service
public class LoginService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private CollegeDao collegeDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CourseOfferingDao courseOfferingDao;
    @Autowired
    private ProfessorDao professorDao;
    @Autowired
    private RegistrarDao registrarDao;
    @Autowired
    private StudyProgramDao studyProgramDao;
    @Autowired
    private DegreeDao degreeDao;
    public Student stuLogin(long studentId,String psd){
        Student student=studentDao.findById(studentId).orElse(null);
        if (student==null){
            return null;
        }
        if(!student.getPassword().equals(psd)){
            return null;
        }
        return student;
    }
    public Professor profLogin(long prodessorId, String psd){
        Professor professor=professorDao.findById(prodessorId).orElse(null);
        if (professor==null){
            return null;
        }
        if(!professor.getPassword().equals(psd)){
            return null;
        }
        return professor;
    }
    public Registrar regLogin(long registrarId, String psd){
        Registrar registrar=registrarDao.findById(registrarId).orElse(null);
        if (registrar==null){
            return null;
        }
        if(!registrar.getPassword().equals(psd)){
            return null;
        }
        return registrar;
    }
}
