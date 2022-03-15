package com.newyang.dormmanage.dao;

import com.newyang.dormmanage.domain.model.Record;
import org.springframework.data.repository.CrudRepository;

public interface RecordRepository extends CrudRepository<Record, Integer> {
}