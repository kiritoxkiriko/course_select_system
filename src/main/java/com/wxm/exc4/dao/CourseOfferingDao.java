package com.wxm.exc4.dao;

import com.wxm.exc4.entity.CourseOffering;
import com.wxm.exc4.entity.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex Wang
 * @date 2019/05/12
 */
@Repository
public interface CourseOfferingDao extends JpaRepository<CourseOffering,Long> {
    public List<CourseOffering> findCourseOfferingsByDegreeIn(List<Degree> degrees);
    public List<CourseOffering> findCourseOfferingsByDegree(Degree degree);
}
