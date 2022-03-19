package com.newyang.dormmanage.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 18:55
 */

@Data
public class DormManagerAddDTO {
    @NotNull
    @Length(max = 15)
    private String userName;

    @NotNull
    @Length(max = 15)
    private String password;

    @NotNull
    private Integer dormBuildId;

    @NotNull
    @Length(max = 10)
    private String name;

    @NotNull
    @Length(max = 1)
    private String sex;

    @Length(max = 11)
    private String tel;


}
