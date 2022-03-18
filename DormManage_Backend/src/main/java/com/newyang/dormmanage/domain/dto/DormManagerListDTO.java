package com.newyang.dormmanage.domain.dto;

import lombok.Data;

import javax.validation.Valid;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 20:07
 */

@Data
public class DormManagerListDTO {
    @Valid
    private PageDTO page;

    private String name;

    private String dormBuildName;
}
