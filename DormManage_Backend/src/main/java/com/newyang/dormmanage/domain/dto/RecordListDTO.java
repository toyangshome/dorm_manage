package com.newyang.dormmanage.domain.dto;

import lombok.Data;

import javax.validation.Valid;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 16:48
 */
@Data
public class RecordListDTO {

    @Valid
    private PageDTO page;
    private String dormBuildName;
    private String dormName;
    private String studentName;
}
