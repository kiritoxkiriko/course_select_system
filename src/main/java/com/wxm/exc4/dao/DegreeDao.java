package com.wxm.exc4.dao;

import com.wxm.exc4.entity.College;
import com.wxm.exc4.entity.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex Wang
 * @date 2019/05/12
 */
@Repository
public interface DegreeDao extends JpaRepository<Degree,Long> {
    public List<Degree> findDegreesByCollege(College college);
}
