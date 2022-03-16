package com.newyang.dormmanage.domain.dto;

import lombok.Data;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 18:40
 */

@Data
public class StudentAddDTO {
    private String stuNum;

    private String dormBuild;
    private String dormName;

    private String name;
    private String password;
    private String sex;
    private String tel;
}
