package com.newyang.dormmanage.dao;

import com.newyang.dormmanage.domain.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RecordRepository extends JpaRepository<Record, Integer> {
}