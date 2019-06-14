package com.wxm.pt.service;

import com.wxm.pt.dao.*;
import com.wxm.pt.entity.College;
import com.wxm.pt.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Alex Wang
 * @date 2019/05/12
 */
@Service
public class StudentService {
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
    public synchronized Student add(long id, @NotNull String name, @NotNull String password, College college){
        return studentDao.save(new Student(id,name,password,college));
    }
    public synchronized List<Student> getAll(){
        return studentDao.findAll();
    }
    public synchronized List<Student> getStudentsBySelectCourseId(long courseId){
        return studentDao.findStudentsBySelectCourseId(courseId);
    }
    public synchronized Student getStudentById(long studentId){
        return studentDao.findById(studentId).orElseGet(null);
    }
    public synchronized List<Student> getStudentByName(String studentName){
        return studentDao.findStudentsByNameLike("%"+studentName+"%");
    }
    public synchronized Student modify(Student student){
        return studentDao.save(student);
    }
    public boolean remove(Student s){
        try {
            studentDao.delete(s);
        } catch (Exception e) {
           return false;
        }
        return true;
    }
    public boolean remove(long id){
        try {
            studentDao.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
