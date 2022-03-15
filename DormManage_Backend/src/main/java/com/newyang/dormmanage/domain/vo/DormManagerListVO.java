package com.newyang.dormmanage.domain.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/15 23:14
 */

@Builder
@Data
public class DormManagerListVO {
    private Integer dormManId;
    private String userName;
    private String dormBuildName;
    private String name;
    private String sex;
    private String tel;
}