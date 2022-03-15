package com.newyang.dormmanage.dao;

import com.newyang.dormmanage.domain.model.DormBuild;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface DormBuildRepository extends CrudRepository<DormBuild, Integer>, JpaSpecificationExecutor<DormBuild> {
}