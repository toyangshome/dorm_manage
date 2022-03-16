package com.newyang.dormmanage.controller;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.domain.model.DormBuild;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/15 17:00
 */

@RestController
@RequestMapping("dorm_build")
@Api("宿舍楼栋API")
public class DormBuildController {

    @PostMapping("list")
    public Response<Page<DormBuild>> list() {
        return null;
    }
}
