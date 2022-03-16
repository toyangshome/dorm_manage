package com.newyang.dormmanage.dao;

import com.newyang.dormmanage.domain.model.DormManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DormManagerRepository extends JpaRepository<DormManager, Integer> {

}