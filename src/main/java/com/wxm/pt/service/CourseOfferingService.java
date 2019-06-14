package com.wxm.pt.service;

import com.wxm.pt.dao.*;
import com.wxm.pt.entity.College;
import com.wxm.pt.entity.CourseOffering;
import com.wxm.pt.entity.Degree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex Wang
 * @date 2019/05/12
 */
@Service
public class CourseOfferingService {
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
    public List<CourseOffering> getCourseOfferingsByCollege(College college){
        List<Degree> degrees=degreeDao.findDegreesByCollege(college);
        if(degrees==null||degrees.isEmpty()){
            return new ArrayList<>();
        }
        return courseOfferingDao.findCourseOfferingsByDegreeIn(degrees);
    }
    public CourseOffering getCourseOfferingById(long courseOfferingId){
        return courseOfferingDao.findById(courseOfferingId).orElse(null);
    }
}
