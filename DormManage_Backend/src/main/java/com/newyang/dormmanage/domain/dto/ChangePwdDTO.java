package com.newyang.dormmanage.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 16:29
 */
@Data
public class ChangePwdDTO {
    @NotNull(message = "Id 不能为空")
    private Integer id;

    @NotBlank
    @Length(max = 18, min = 3, message = "密码长度应该在 3-18 范围内")
    private String oldPassword;

    @NotBlank
    @Length(max = 18, min = 3, message = "密码长度应该在 3-18 范围内")
    private String newPassword;

    @NotNull
    private Integer role;
}
