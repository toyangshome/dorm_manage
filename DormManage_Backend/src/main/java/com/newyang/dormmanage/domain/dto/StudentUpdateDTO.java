package com.newyang.dormmanage.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 18:17
 */

@Data
public class StudentUpdateDTO {

    @NotNull
    private Integer studentId;
    @NotBlank
    @Length(min = 1, max = 15)
    private String name;

    private String dormName;

    private String dormBuildId;

    private String sex;

    private String tel;
}
