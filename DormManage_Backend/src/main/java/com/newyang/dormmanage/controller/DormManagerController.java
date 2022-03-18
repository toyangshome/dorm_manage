package com.newyang.dormmanage.controller;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.domain.dto.DormManagerAddDTO;
import com.newyang.dormmanage.domain.dto.DormManagerListDTO;
import com.newyang.dormmanage.domain.dto.DormManagerUpdateDTO;
import com.newyang.dormmanage.domain.model.DormBuild;
import com.newyang.dormmanage.domain.model.DormManager;
import com.newyang.dormmanage.domain.vo.DormManagerListVO;
import com.newyang.dormmanage.domain.vo.DormManagerVO;
import com.newyang.dormmanage.service.DormManagerService;
import com.newyang.dormmanage.service.impl.DormManagerServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("dorm_manager")
@Api(tags = "宿舍管理员API")
public class DormManagerController {
    private final DormManagerService dmService;

    @Autowired
    public DormManagerController (DormManagerServiceImpl dormManagerService) {
        this.dmService = dormManagerService;
    }

    @PostMapping("list")
    public Response<Page<DormManagerListVO>> list (@RequestBody @Validated DormManagerListDTO params) {
        PageRequest pageRequest = PageRequest.of(params.getPage().getCurrent(), params.getPage().getPageSize());

        DormManager exampleQuery = new DormManager();
        exampleQuery.setName(params.getName());
        DormBuild dormBuild = new DormBuild();
        dormBuild.setDormBuildName(params.getDormBuildName());
        exampleQuery.setDormBuild(dormBuild);

        Page<DormManagerListVO> res = dmService.list(pageRequest, Example.of(exampleQuery));
        return Response.success(res);
    }

    @PostMapping("update")
    public Response<DormManagerVO> update (@RequestBody @Validated DormManagerUpdateDTO params) {
        return null;
    }

    @PostMapping("add")
    public Response<Void> add (@RequestBody @Validated DormManagerAddDTO params) {
        return null;
    }

    @PostMapping("delete/{id}")
    public Response<Void> delete (@PathVariable("id") Integer id) {
        return null;
    }
}
