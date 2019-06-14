package com.wxm.pt.dao;

import com.wxm.pt.entity.Professor;
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
