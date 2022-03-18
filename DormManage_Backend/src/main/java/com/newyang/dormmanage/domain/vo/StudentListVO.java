package com.newyang.dormmanage.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 14:34
 */

@Data
@Accessors(chain = true)
public class StudentListVO {

    private Integer studentId;

    private String stuNum;

    private String name;

    private String dormBuildName;

    private String dormName;

    private String sex;

    private String tel;
}
