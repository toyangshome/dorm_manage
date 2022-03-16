package com.newyang.dormmanage.handler;

import lombok.Data;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 10:33
 */

@Data
public class SysLogEntity {
    private String className;
    private String methodName;
    private String params;
    private Long executeTime;
    private String remark;
    private String createDate;
    private String remoteAddr;
}