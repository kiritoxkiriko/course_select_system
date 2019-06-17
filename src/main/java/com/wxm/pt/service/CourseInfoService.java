package com.wxm.pt.service;

import com.wxm.pt.dao.CourseInfoDao;
import com.wxm.pt.entity.Course;
import com.wxm.pt.entity.CourseInfo;
import com.wxm.pt.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Alex Wang
 * @date 2019/06/17
 */
@Service
public class CourseInfoService {
    @Autowired
    private CourseInfoDao courseInfoDao;
    public synchronized CourseInfo getCourseInfoByCourseAndStudent(Course course, Student student){
        return courseInfoDao.findCourseInfoByCourseAndStudent(course,student);
    }
    public synchronized CourseInfo getCourseInforByCourseIdAndStudentId(long courseId,long studentId){
        return courseInfoDao.findCourseInfoByCourseIdAndStudentId(courseId, studentId);
    }
    public synchronized Map<Student,CourseInfo> getCourseInfoMapByCourse(Course course){
        Map<Student,CourseInfo> courseInfoMap=new ConcurrentHashMap<>();
        List<CourseInfo> courseInfos=courseInfoDao.findCourseInfosByCourse(course);
        for(CourseInfo ci:courseInfos){
            courseInfoMap.put(ci.getStudent(),ci);
        }
        return courseInfoMap;
    }
    public synchronized Map<Course,CourseInfo> getCourseInfoMapByCourseId(long courseId){
        Map<Course,CourseInfo> courseInfoMap=new ConcurrentHashMap<>();
        List<CourseInfo> courseInfos=courseInfoDao.findCourseInfosByCourseId(courseId);
        for(CourseInfo ci:courseInfos){
            courseInfoMap.put(ci.getCourse(),ci);
        }
        return courseInfoMap;
    }
    public synchronized CourseInfo add(Course course,Student student,double score){
        CourseInfo courseInfo=courseInfoDao.findCourseInfoByCourseAndStudent(course,student);
        if(courseInfo!=null){
            return courseInfo;
        }
        return courseInfoDao.save(new CourseInfo(course,student,score));
    }
    public synchronized CourseInfo modify(Course course,Student student,double score){
        CourseInfo courseInfo=courseInfoDao.findCourseInfoByCourseAndStudent(course,student);
        if(courseInfo==null){
            return null;
        }
        courseInfo.setScore(score);
        return courseInfoDao.save(courseInfo);
    }
}
