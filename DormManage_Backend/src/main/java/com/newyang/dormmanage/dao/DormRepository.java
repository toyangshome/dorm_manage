package com.newyang.dormmanage.dao;

import com.newyang.dormmanage.domain.model.Dorm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DormRepository extends JpaRepository<Dorm, Integer> {
}
