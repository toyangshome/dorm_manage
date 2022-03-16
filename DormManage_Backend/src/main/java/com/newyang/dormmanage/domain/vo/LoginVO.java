package com.newyang.dormmanage.domain.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 13:10
 */

public class LoginVO {
    @Accessors(chain = true)
    @Data
    public static class Admin {
        private int adminId;
        private String username;
        private String name;
        private String sex;
        private String tel;
    }
    @Data
    @Accessors(chain = true)
    public static class DormManager {
        private int dormManId;
        private String userName;
        private String dormBuildName;
        private int dormBuildId;
        private String name;
        private String sex;
        private String tel;
    }
    @Data
    @Accessors(chain = true)
    public static class Student {
        private int studentId;
        private String stuNum;
        private String name;
        private int dormBuildId;
        private String dormName;
        private String sex;
        private String tel;
    }
}
