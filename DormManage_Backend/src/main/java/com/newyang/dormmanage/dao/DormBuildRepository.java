package com.newyang.dormmanage.dao;

import com.newyang.dormmanage.domain.model.DormBuild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface DormBuildRepository extends JpaRepository<DormBuild, Integer>, JpaSpecificationExecutor<DormBuild> {
}