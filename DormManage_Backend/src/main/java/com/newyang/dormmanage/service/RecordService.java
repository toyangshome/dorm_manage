package com.newyang.dormmanage.service;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.domain.model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RecordService {
    Page<Record> list(PageRequest pageRequest);
    void delete(Integer recordId);
    Response<Void> update(Record record);
}
