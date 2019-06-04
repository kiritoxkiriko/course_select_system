package com.wxm.exc4.service;

import com.wxm.exc4.dao.*;
import com.wxm.exc4.entity.College;
import com.wxm.exc4.entity.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Alex Wang
 * @date 2019/05/12
 */
@Service
public class ProfessorService {
    @Autowired
    private ProfessorDao professorDao;
    @Autowired
    private CollegeDao collegeDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CourseOfferingDao courseOfferingDao;
    @Autowired
    private StudentDao studentrDao;
    @Autowired
    private RegistrarDao registrarDao;
    @Autowired
    private StudyProgramDao studyProgramDao;
    @Autowired
    private DegreeDao degreeDao;
    public Professor add(long id, @NotNull String name, @NotNull String password, College college){
        return professorDao.save(new Professor(id,name,password,college));
    }
    public List<Professor> getAll(){
        return professorDao.findAll();
    }
    public Professor getProfessorById(long professorId){
        return professorDao.findById(professorId).orElseGet(null);
    }
    public List<Professor> getProfessorByName(String professorName){
        return professorDao.findProfessorsByNameLike("%"+professorName+"%");
    }
    public Professor modify(Professor professor){
        return professorDao.save(professor);
    }
    public boolean remove(Professor professor){
        try {
            professorDao.delete(professor);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public boolean remove(long professorId){
        try {
            professorDao.deleteById(professorId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
