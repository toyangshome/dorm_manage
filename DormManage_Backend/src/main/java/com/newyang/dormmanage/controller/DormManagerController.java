package com.newyang.dormmanage.controller;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.commons.ResStatus;
import com.newyang.dormmanage.domain.vo.DormManagerListVO;
import com.newyang.dormmanage.service.DormManagerService;
import com.newyang.dormmanage.service.impl.DormManagerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/15 17:00
 */

@RestController
@RequestMapping("dorm_manager")
@Api("宿舍管理员API")
public class DormManagerController {
    private final DormManagerService dmService;
    @Autowired
    public DormManagerController(DormManagerServiceImpl dormManagerService) {
        this.dmService = dormManagerService;
    }

    @PostMapping("list")
    public Response<Page<DormManagerListVO>> list(int pageSize, int current) {
        if (pageSize < 0 || pageSize > 20 || current < 0) {
            return Response.failure(ResStatus.PARAM_IS_INVALID);
        }
        Page<DormManagerListVO> res = dmService.list(PageRequest.of(current, pageSize));
        return Response.success(res);
    }
}
