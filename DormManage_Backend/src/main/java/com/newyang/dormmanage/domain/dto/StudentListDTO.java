package com.newyang.dormmanage.domain.dto;

import lombok.Data;

import javax.validation.Valid;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 17:08
 */

@Data
public class StudentListDTO {
    @Valid
    private PageDTO page;

    private String dormBuildName;
    private String dormName;
    private String studentName;
}
