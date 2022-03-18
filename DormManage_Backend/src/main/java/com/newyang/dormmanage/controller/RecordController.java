package com.newyang.dormmanage.controller;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.domain.dto.RecordAddDTO;
import com.newyang.dormmanage.domain.dto.RecordListDTO;
import com.newyang.dormmanage.domain.dto.RecordUpdateDTO;
import com.newyang.dormmanage.domain.model.Record;
import com.newyang.dormmanage.domain.vo.RecordListVO;
import com.newyang.dormmanage.service.RecordService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 16:44
 */

@RestController
@RequestMapping("record")
@Api(tags = "考勤记录API")
public class RecordController {
    private final RecordService recordService;

    public RecordController (RecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping("list")
    public Response<Page<RecordListVO>> list (@RequestBody @Validated RecordListDTO params) {
        // 分页逻辑
        PageRequest pageRequest = PageRequest.of(params.getPage().getCurrent(), params.getPage().getPageSize());
        Record queryExample = new Record();
        // 根据字段进行查询匹配
        queryExample.setDormName(params.getDormBuildName());
        queryExample.setStudentName(params.getStudentName());
        Example<Record> example = Example.of(queryExample);

        Page<RecordListVO> list = recordService.list(pageRequest, example);
        return Response.success(list);
    }

    @PostMapping("update")
    public Response<Record> update (@RequestBody @Validated RecordUpdateDTO parmas) {

        return null;
    }

    @PostMapping("add")
    public Response<Void> add (@RequestBody @Validated RecordAddDTO params) {
        return null;
    }

    @PostMapping("delete/{id}")
    public Response<Void> delete (@PathVariable("id") Integer id) {
        return null;
    }
}
