package com.wxm.exc4.dao;

import com.wxm.exc4.entity.Professor;
import com.wxm.exc4.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex Wang
 * @date 2019/05/12
 */
@Repository
public interface ProfessorDao extends JpaRepository<Professor,Long> {
    public List<Professor> findProfessorsByNameLike(String name);
}
