package com.newyang.dormmanage.dao;

import com.newyang.dormmanage.domain.model.DormManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DormManagerRepository extends CrudRepository<DormManager, Integer> {
}