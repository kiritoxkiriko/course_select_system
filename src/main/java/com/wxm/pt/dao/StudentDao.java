package com.wxm.pt.dao;

import com.wxm.pt.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex Wang
 * @date 2019/05/11
 */
@Repository
public interface StudentDao extends JpaRepository<Student,Long> {
    public List<Student> findStudentsByNameLike(String name);
    @Query("select distinct sp.student from StudyProgram sp where 0<(select count(c) from sp.primaryCourses c where c.id=:courseId)")
    public List<Student> findStudentsBySelectCourseId(@Param("courseId") long courseId);
}
