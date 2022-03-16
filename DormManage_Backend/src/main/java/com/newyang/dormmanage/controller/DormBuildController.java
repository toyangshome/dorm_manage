package com.newyang.dormmanage.controller;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.domain.dto.DormBuildAddDTO;
import com.newyang.dormmanage.domain.dto.DormBuildListDTO;
import com.newyang.dormmanage.domain.dto.DormBuildUpdateDTO;
import com.newyang.dormmanage.domain.model.DormBuild;
import com.newyang.dormmanage.service.DormBuildService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/15 17:00
 */

@RestController
@RequestMapping("dorm_build")
@Api(tags = "宿舍楼栋API")
public class DormBuildController {
    private final DormBuildService dormBuildService;

    public DormBuildController (DormBuildService dormBuildService) {
        this.dormBuildService = dormBuildService;
    }

    @PostMapping("list")
    public Response<Page<DormBuild>> list (
            @RequestBody
            @Validated
                    DormBuildListDTO params
    ) {
        PageRequest pageRequest = PageRequest.of(params.getPage().getCurrent(), params.getPage().getPageSize());
        DormBuild exampleQuery = new DormBuild();
        exampleQuery.setDormBuildName(params.getDormBuildName());
        Example<DormBuild> example = Example.of(exampleQuery);
        Page<DormBuild> list = dormBuildService.list(pageRequest, example);
        return Response.success(list);
    }

    @PostMapping("add")
    public Response<Void> add (
            @RequestBody
            @Validated
                    DormBuildAddDTO params
    ) {

        return Response.success();
    }

    @PostMapping("delete/{id}")
    public Response<Void> delete (@PathVariable("id") Integer id) {
        return null;
    }

    @PostMapping("update")
    public Response<DormBuild> update (
            @RequestBody
            @Validated
                    DormBuildUpdateDTO params
    ) {
        return null;
    }

}
