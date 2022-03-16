package com.newyang.dormmanage.service.impl;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.dao.RecordRepository;
import com.newyang.dormmanage.domain.model.Record;
import com.newyang.dormmanage.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 0:28
 */

@Service
public class RecordServiceImpl implements RecordService {
    private final RecordRepository rcRepo;
    @Autowired
    public RecordServiceImpl(RecordRepository recordRepository) {
        this.rcRepo = recordRepository;
    }

    @Override
    public Page<Record> list (PageRequest pageRequest) {
        return rcRepo.findAll(pageRequest);
    }

    @Override
    public void delete (Integer recordId) {
        rcRepo.deleteById(recordId);
    }

    @Override
    public Response<Void> update (Record newRecord) {
        Optional<Record> oldRecord = rcRepo.findById(newRecord.getId());
        if (oldRecord.isPresent()) {
            Record rc = oldRecord.get();
            rc.setDate(newRecord.getDate());
            rc.setDetail(newRecord.getDetail());
            rc.setDormBuildId(newRecord.getDormBuildId());
            rc.setDormName(newRecord.getDormName());
            rc.setStudentNumber(newRecord.getStudentNumber());
            rc.setStudentName(newRecord.getStudentName());
            rcRepo.saveAndFlush(rc);
            return Response.success();
        }
        return Response.failure(-1,"目标不存在");
    }
}
