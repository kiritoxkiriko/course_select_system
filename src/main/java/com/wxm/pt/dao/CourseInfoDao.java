package com.wxm.pt.dao;

import com.wxm.pt.entity.Course;
import com.wxm.pt.entity.CourseInfo;
import com.wxm.pt.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex Wang
 * @date 2019/06/17
 */
@Repository
public interface CourseInfoDao extends JpaRepository<CourseInfo,Long> {
    public CourseInfo findCourseInfoByCourseAndStudent(Course course, Student student);
    public CourseInfo findCourseInfoByCourse_IdAndStudent_Id(long courseId, long studentId);
    public List<CourseInfo> findCourseInfosByCourse(Course course);
    public List<CourseInfo> findCourseInfosByCourse_Id(long courseId);
}
