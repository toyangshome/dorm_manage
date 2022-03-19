package com.newyang.dormmanage.service;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.domain.dto.DormManagerAddDTO;
import com.newyang.dormmanage.domain.dto.DormManagerUpdateDTO;
import com.newyang.dormmanage.domain.model.DormManager;
import com.newyang.dormmanage.domain.vo.DormManagerListVO;
import com.newyang.dormmanage.domain.vo.DormManagerVO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/15 18:39
 */

public interface DormManagerService {
    Page<DormManagerListVO> list(PageRequest pageRequest, Example<DormManager> example);
    void delete(Integer id);
    Response<DormManagerVO> update(DormManagerUpdateDTO dormManager);

    Response<DormManagerVO> add (DormManagerAddDTO params);
}
