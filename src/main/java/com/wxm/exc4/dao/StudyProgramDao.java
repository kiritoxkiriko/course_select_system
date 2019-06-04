package com.wxm.exc4.dao;

import com.wxm.exc4.entity.CourseOffering;
import com.wxm.exc4.entity.Student;
import com.wxm.exc4.entity.StudyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex Wang
 * @date 2019/05/12
 */
@Repository
public interface StudyProgramDao extends JpaRepository<StudyProgram,Long> {
    public List<StudyProgram> findStudyProgramsByStudentAndCourseOffering(Student student, CourseOffering courseOffering);
}
