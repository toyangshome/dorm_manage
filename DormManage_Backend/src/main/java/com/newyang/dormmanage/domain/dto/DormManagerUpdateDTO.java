package com.newyang.dormmanage.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 18:55
 */

@Data
public class DormManagerUpdateDTO {
    @NotNull
    private Integer dormManId;

    private Integer dormBuildId;

    @NotNull
    private String name;

    @NotNull
    private String sex;

    @NotNull
    private String userName;

    @NotNull
    private String tel;
}
