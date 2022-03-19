package com.newyang.dormmanage.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 18:38
 */

@Data
public class DormBuildAddDTO {
    @NotNull
    private String dormBuildName;
    private String detail;
}
