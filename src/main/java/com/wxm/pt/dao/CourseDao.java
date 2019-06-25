package com.wxm.pt.dao;

import com.wxm.pt.entity.Course;
import com.wxm.pt.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex Wang
 * @date 2019/05/12
 */
@Repository
public interface CourseDao extends JpaRepository<Course,Long> {
    public long countByName(String name);
    public List<Course> findCoursesByProfessor(Professor professor);
    public List<Course> findCoursesByProfessorAndSelectNumGreaterThan(Professor professor, int min);
}
