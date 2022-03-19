package com.newyang.dormmanage.dao;

import com.newyang.dormmanage.domain.model.DormBuild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DormBuildRepository extends JpaRepository<DormBuild, Integer>, JpaSpecificationExecutor<DormBuild> {

    @Transactional
    @Query(value = "SELECT dormBuildName FROM DormBuild WHERE dormBuildId = :id")
    String queryDormBuildName (@Param("id") Integer id);

}