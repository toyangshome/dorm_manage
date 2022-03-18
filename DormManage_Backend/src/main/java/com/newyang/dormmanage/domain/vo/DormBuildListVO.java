package com.newyang.dormmanage.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 14:30
 */
@Data
@Accessors(chain = true)
public class DormBuildListVO {
    private Integer dormBuildId;
    private String dormBuildName;
}
