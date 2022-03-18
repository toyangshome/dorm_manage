package com.newyang.dormmanage.domain.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 14:38
 */

@Data
@Accessors(chain = true)
public class RecordListVO {
    private Integer recordId;

    private String studentNumber;

    private String studentName;

    private String dormBuildName;

    private String dormName;

    private LocalDate date;

    private String detail;
}
