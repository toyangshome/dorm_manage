package com.newyang.dormmanage.service;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.domain.model.DormManager;
import com.newyang.dormmanage.domain.vo.DormManagerListVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.data.querydsl.QPageRequest;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/15 18:39
 */

public interface DormManagerService {
    Page<DormManagerListVO> list(PageRequest pageRequest);
    void delete(Integer id);
    Response<Void> update(DormManager dormManager);
}
