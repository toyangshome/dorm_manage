package com.newyang.dormmanage.domain.vo;

import com.newyang.dormmanage.domain.model.DormManager;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 18:47
 */

@Data
@Accessors(chain = true)
public class DormManagerVO {
    private Integer dormManId;
    private String userName;
    private String dormBuildName;
    private Integer dormBuildId;
    private String name;
    private String sex;
    private String tel;
}
