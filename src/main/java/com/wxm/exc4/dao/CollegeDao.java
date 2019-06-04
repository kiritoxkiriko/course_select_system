package com.wxm.exc4.dao;

import com.wxm.exc4.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex Wang
 * @date 2019/05/12
 */
@Repository
public interface CollegeDao extends JpaRepository<College,Long> {
}
