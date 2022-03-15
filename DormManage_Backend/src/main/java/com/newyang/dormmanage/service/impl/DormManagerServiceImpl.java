package com.newyang.dormmanage.service.impl;

import com.newyang.dormmanage.dao.DormManagerRepository;
import com.newyang.dormmanage.domain.vo.DormManagerListVO;
import com.newyang.dormmanage.service.DormManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/15 23:16
 */
@Service
public class DormManagerServiceImpl implements DormManagerService {
    private final DormManagerRepository dmRepo;

    @Autowired
    public DormManagerServiceImpl (DormManagerRepository dormManagerRepository) {
        this.dmRepo = dormManagerRepository;
    }

    @Override
    public Page<DormManagerListVO> list (PageRequest pageRequest) {
        Page<DormManagerListVO> result = dmRepo
                .findAll(pageRequest)
                .map((item) -> DormManagerListVO
                        .builder()
                        .dormManId(item.getDormManId())
                        .sex(item.getSex())
                        .tel(item.getTel())
                        .dormBuildName(item.getDormBuildName())
                        .userName(item.getUserName())
                        .name(item.getName()).build());

        return result;
    }
}
