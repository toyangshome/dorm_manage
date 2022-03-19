package com.newyang.dormmanage.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 18:53
 */

@Data
public class RecordUpdateDTO {
    @NotNull
    private Integer recordId;
    @NotNull
    private String studentNumber;
    @NotNull
    private LocalDate date;
    @Length(max = 200)
    private String detail;
}
