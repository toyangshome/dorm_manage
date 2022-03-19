package com.newyang.dormmanage.service.impl;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.dao.DormBuildRepository;
import com.newyang.dormmanage.dao.RecordRepository;
import com.newyang.dormmanage.dao.StudentRepository;
import com.newyang.dormmanage.domain.dto.RecordUpdateDTO;
import com.newyang.dormmanage.domain.model.DormBuild;
import com.newyang.dormmanage.domain.model.Record;
import com.newyang.dormmanage.domain.model.Student;
import com.newyang.dormmanage.domain.vo.RecordListVO;
import com.newyang.dormmanage.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 0:28
 */

@Service
public class RecordServiceImpl implements RecordService {
    private final RecordRepository rcRepo;
    private final DormBuildRepository dbRepo;
    private final StudentRepository stRepo;

    @Autowired
    public RecordServiceImpl (RecordRepository recordRepository,
                              DormBuildRepository dormBuildRepository,
                              StudentRepository studentRepository) {
        this.rcRepo = recordRepository;
        this.dbRepo = dormBuildRepository;
        this.stRepo = studentRepository;
    }

    @Override
    public Page<RecordListVO> list (PageRequest pageRequest, Example<Record> example) {
        return rcRepo.findAll(example, pageRequest).map((item) -> new RecordListVO()
                .setRecordId(item.getId())
                .setDate(item.getDate())
                .setDetail(item.getDetail())
                .setDormName(item.getDormName())
                .setDormBuildName(dbRepo.queryDormBuildName(item.getDormBuildId()))
                .setStudentName(item.getStudentName())
                .setDormBuildId(item.getDormBuildId())
                .setStudentNumber(item.getStudentNumber()));
    }


    @Override
    public void delete (Integer recordId) {
        rcRepo.deleteById(recordId);
    }

    @Override
    public Response<Record> update (@Valid RecordUpdateDTO newRecord) {
        Student queryParams = new Student();
        queryParams.setStuNum(newRecord.getStudentNumber());
        Student std = stRepo.findOne(Example.of(queryParams))
                .orElseThrow(() -> new RuntimeException("目标学号不存在"));
        // wrap record object
        Record record = new Record();
        record.setDate(newRecord.getDate());
        record.setDormBuildId(std.getDormBuildId());
        record.setStudentNumber(std.getStuNum());
        record.setStudentName(std.getName());
        record.setDetail(newRecord.getDetail());
        record.setDormName(std.getDormName());
        record.setId(newRecord.getRecordId());

        return Response.success(rcRepo.saveAndFlush(record));
    }

    @Override
    public Record add (Integer dormBuildId, String studentNumber, String detail) {
        // query DormBuild info
        DormBuild dormBuild = dbRepo
                .findById(dormBuildId)
                .orElseThrow(() -> new NullPointerException("参数错误"));
        // query Student info
        Student queryParams = new Student();
        queryParams.setStuNum(studentNumber);
        Student student = stRepo.findOne(Example.of(queryParams))
                .orElseThrow(() -> new NullPointerException("参数错误"));


        Record record = new Record();
        record.setDate(LocalDate.now());
        record.setDormBuildId(dormBuild.getId());
        record.setDormName(student.getDormName());
        record.setDetail(detail);
        record.setStudentName(student.getName());
        record.setStudentNumber(student.getStuNum());
        return rcRepo.save(record);
    }

}
