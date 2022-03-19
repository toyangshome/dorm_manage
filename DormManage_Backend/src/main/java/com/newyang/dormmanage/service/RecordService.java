package com.newyang.dormmanage.service;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.domain.dto.RecordUpdateDTO;
import com.newyang.dormmanage.domain.model.Record;
import com.newyang.dormmanage.domain.vo.RecordListVO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RecordService {
    /**
     * @param pageRequest
     * @param example
     * @return
     */
    Page<RecordListVO> list (PageRequest pageRequest, Example<Record> example);

    /**
     * @param recordId
     */
    void delete (Integer recordId);

    /**
     * @param record
     * @return
     */
    Response<Record> update (RecordUpdateDTO record);

    Record add(Integer dormBuildId,String studentNumber,String detail);
}
