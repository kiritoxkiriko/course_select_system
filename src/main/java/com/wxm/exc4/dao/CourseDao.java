package com.wxm.exc4.dao;

import com.wxm.exc4.entity.Course;
import com.wxm.exc4.entity.Professor;
import com.wxm.exc4.service.CourseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex Wang
 * @date 2019/05/12
 */
@Repository
public interface CourseDao extends JpaRepository<Course,Long> {
    public List<Course> findCoursesByProfessor(Professor professor);
    public List<Course> findCoursesByProfessorAndSelectNumGreaterThan(Professor professor, int min);
}
