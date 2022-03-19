package com.newyang.dormmanage.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 18:40
 */

@Data
public class StudentAddDTO {
    @NotNull
    @Length(max = 3, min = 3)
    private String stuNum;

    @NotNull
    private Integer dormBuildId;

    @Length(max = 5)
    private String dormName;
    @NotNull
    @Length(max = 10)
    private String name;
    @NotNull
    @Length(max = 18)
    private String password;

    @Length(max = 1)
    private String sex;

    @Length(max = 11)
    private String tel;
}
