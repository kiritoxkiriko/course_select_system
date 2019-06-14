package com.wxm.pt.dao;

import com.wxm.pt.entity.Registrar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alex Wang
 * @date 2019/05/12
 */
@Repository
public interface RegistrarDao extends JpaRepository<Registrar,Long> {
}
