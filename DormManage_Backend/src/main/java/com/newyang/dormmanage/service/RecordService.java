package com.newyang.dormmanage.service;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.domain.model.Record;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RecordService {
    /**
     * @param pageRequest
     * @param example
     * @return
     */
    Page<Record> list (PageRequest pageRequest, Example<Record> example);

    /**
     * @param recordId
     */
    void delete (Integer recordId);

    /**
     * @param record
     * @return
     */
    Response<Void> update (Record record);
}
