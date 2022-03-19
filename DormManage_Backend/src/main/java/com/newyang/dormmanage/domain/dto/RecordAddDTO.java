package com.newyang.dormmanage.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 18:52
 */

@Data
public class RecordAddDTO {
    @NotNull
    private String studentNumber;

    private String detail;
    @NotNull
    private Integer dormBuildId;
}
