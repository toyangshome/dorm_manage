package com.newyang.dormmanage.domain.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 17:17
 */


@Data
public class PageDTO {
    @NotNull
    @Min(0)
    @Max(20)
    private Integer pageSize;
    @NotNull
    @Min(0)
    private Integer current;
}
