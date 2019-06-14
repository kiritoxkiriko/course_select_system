package com.wxm.pt.service;

import com.wxm.pt.dao.*;
import com.wxm.pt.entity.College;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alex Wang
 * @date 2019/05/12
 */
@Service
public class CollegeService {
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
    public List<College> getAllColleges(){
        return collegeDao.findAll();
    }
    public College getCollegeById(long collegeId){
        return collegeDao.findById(collegeId).orElse(null);
    }
}
