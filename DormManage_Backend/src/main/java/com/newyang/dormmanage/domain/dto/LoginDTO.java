package com.newyang.dormmanage.domain.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 12:59
 */

@Data
public class LoginDTO {
    @NotBlank
    @Length(max = 18,message = "Username not allow")
    private String username;

    @NotBlank
    @Length(min = 3,max = 18)
    private String password;

    @NotNull
    private Integer role;
}
