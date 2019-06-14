package com.wxm.pt.service;

import com.wxm.pt.dao.*;
import com.wxm.pt.entity.Course;
import com.wxm.pt.entity.CourseOffering;
import com.wxm.pt.entity.Student;
import com.wxm.pt.entity.StudyProgram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex Wang
 * @date 2019/05/12
 */
@Service
public class StudyProgramService {
    public static final int  MAX_PRIMARY_COURSE_NUM= 4;
    public static final int MAX_SECONDARY_COURSE_NUM = 2;
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
    @Autowired
    private CourseService courseService;

    public synchronized boolean addPrimaryCourse(StudyProgram studyProgram, Course course) {
        List<Course> courses = studyProgram.getCourseOffering().getCourses();
        if (!courses.contains(course)) {
            System.out.println("!contain");
            return false;
        }
        if (courseService.isFull(course)) {
            System.out.println("full");
            return false;
        }
        if (!canAddPrimaryCourse(studyProgram)) {
            System.out.println("!canAdd");
            return false;
        }
        List<Course> primaryCourse = studyProgram.getPrimaryCourses();
        List<Course> secondaryCourse = studyProgram.getSecondaryCourses();
        if(primaryCourse.contains(course)||secondaryCourse.contains(course)){
            System.out.println("reAdd");
            return false;
        }
        if (courseService.isCollide(course, primaryCourse)) {
            System.out.println("collide");
            return false;
        }
        if (!primaryCourse.add(course)) {
            System.out.println("!add");
            return false;
        }
        course.setSelectNum(course.getSelectNum()+1);
        if(courseDao.save(course)==null){
            System.out.println("!courseSave");
            return false;
        }
        return studyProgramDao.save(studyProgram) != null;
    }
    public synchronized boolean removePrimaryCourse(StudyProgram studyProgram, Course course){
        if(studyProgram.isSubmit()){
            return false;
        }
        List<Course> courses = studyProgram.getCourseOffering().getCourses();
        if (!courses.contains(course)) {
            return false;
        }
        List<Course> primaryCourse = studyProgram.getPrimaryCourses();
        if (!primaryCourse.remove(course)) {
            return false;
        }
        course.setSelectNum(course.getSelectNum()-1);
        if(courseDao.save(course)==null){
            return false;
        }
        return studyProgramDao.save(studyProgram) != null;
    }

    public synchronized boolean removeSecondaryCourse(StudyProgram studyProgram, Course course) {
        if(studyProgram.isSubmit()){
            return false;
        }
        List<Course> courses = studyProgram.getCourseOffering().getCourses();
        if (!courses.contains(course)) {
            return false;
        }
        List<Course> secondaryCourses = studyProgram.getSecondaryCourses();
        if (!secondaryCourses.remove(course)) {
            return false;
        }
        return studyProgramDao.save(studyProgram) != null;
    }

    public synchronized boolean addSecondaryCourse(StudyProgram studyProgram, Course course) {
        List<Course> courses = studyProgram.getCourseOffering().getCourses();
        if (!courses.contains(course)) {
            System.out.println("!contain");
            return false;
        }
        if (courseService.isFull(course)) {
            System.out.println("full");
            return false;
        }
        if (!canAddSecondaryCourse(studyProgram)) {
            System.out.println("!canAdd");
            return false;
        }
        List<Course> secondaryCourses = studyProgram.getSecondaryCourses();
        List<Course> primaryCourses=studyProgram.getPrimaryCourses();
        if(secondaryCourses.contains(course)||primaryCourses.contains(course)){
            System.out.println("reAdd");
            return false;
        }
        if (courseService.isCollide(course, secondaryCourses)) {
            System.out.println("collide");
            return false;
        }
        if (!secondaryCourses.add(course)) {
            System.out.println("!add");
            return false;
        }
        return studyProgramDao.save(studyProgram) != null;
    }

    public synchronized boolean sumbit(StudyProgram studyProgram) {
        if (studyProgram.isSubmit()) {
            return false;
        } else {
            if (!canAddPrimaryCourse(studyProgram)) {
                studyProgram.setSubmit(true);
                return studyProgramDao.save(studyProgram) != null;
            }
            return false;
        }
    }

    public synchronized boolean check(StudyProgram studyProgram) {
        if (!studyProgram.isSubmit()) {
            return false;
        }
        List<Course> primaryCourses = studyProgram.getPrimaryCourses();
        List<Course> secondaryCourses = studyProgram.getSecondaryCourses();
        List<Course> tempList=new ArrayList<>();//临时list，因为遍历时不能修改
        for (Course c
                : primaryCourses) {
            if (!courseService.canOpen(c)) {
                tempList.add(c);//遍历时不能修改，先加到临时数组里
            }
        }
        primaryCourses.removeAll(tempList);
        tempList.clear();
        for (Course c :
                secondaryCourses) {
            if (courseService.canOpen(c)) {
//                addPrimaryCourse(studyProgram, c);//遍历时不能修改
                tempList.add(c);
            }
//            studyProgram = studyProgramDao.findById(studyProgram.getId()).get();
            if (!canAddPrimaryCourse(studyProgram)) {
                break;
            }
        }
        for(Course c:
            tempList){
            addPrimaryCourse(studyProgram, c);//遍历后添加课程
        }
        if (studyProgramDao.save(studyProgram)==null) {
            System.out.println("!save");
            return false;
        }
        return !canAddPrimaryCourse(studyProgram);
    }

    public synchronized boolean canAddPrimaryCourse(StudyProgram studyProgram) {
        List<Course> courses = studyProgram.getPrimaryCourses();
        return courses.size() < studyProgram.getCourseOffering().getMaxSelectNum();
    }

    public synchronized boolean canAddSecondaryCourse(StudyProgram studyProgram) {
        if (studyProgram.isSubmit()) {
            return false;
        }
        List<Course> courses = studyProgram.getSecondaryCourses();
        return courses.size() < MAX_SECONDARY_COURSE_NUM;
    }

    public synchronized StudyProgram add(Student student, CourseOffering courseOffering) {
        List<StudyProgram> studyPrograms=studyProgramDao.findStudyProgramsByStudentAndCourseOffering(student,courseOffering);
        if (studyPrograms.size()>0){
            return studyPrograms.get(0);
        }
        StudyProgram studyProgram = new StudyProgram(student, courseOffering);
        return studyProgramDao.save(studyProgram);
    }
    public synchronized StudyProgram getByStudentAndCourseOffering(Student student,CourseOffering courseOffering){
        List<StudyProgram> studyPrograms=studyProgramDao.findStudyProgramsByStudentAndCourseOffering(student,courseOffering);
        return studyPrograms.size()>0?studyPrograms.get(0):null;
    }
    public synchronized StudyProgram getStudeyProgramById(long studyProgramId){
        return studyProgramDao.findById(studyProgramId).orElse(null);
    }
}
