package com.newyang.dormmanage.domain.vo;

import com.newyang.dormmanage.domain.model.Student;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 18:23
 */

@Data
@Accessors(chain = true)
public class StudentVO {

    public static StudentVO convertFromStudent (Student source) {
        StudentVO studentVO = new StudentVO();
        BeanUtils.copyProperties(source, studentVO);
        return studentVO;
    }

    private Integer studentId;

    private String stuNum;

    private String name;

    private Integer dormBuildId;

    private String dormName;

    private String sex;

    private String tel;
}
