package com.newyang.dormmanage.service.impl;

import com.newyang.dormmanage.commons.ResStatus;
import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.dao.DormBuildRepository;
import com.newyang.dormmanage.domain.model.DormBuild;
import com.newyang.dormmanage.service.DormBuildService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 0:26
 */

@Service
public class DormBuildServiceImpl implements DormBuildService {
    private final DormBuildRepository dmbRepo;

    @Autowired
    public DormBuildServiceImpl (DormBuildRepository dormBuildRepository) {
        this.dmbRepo = dormBuildRepository;
    }

    @Override
    public Page<DormBuild> list (PageRequest pageRequest) {
        return dmbRepo.findAll(pageRequest);
    }

    @Override
    public void delete (Integer dormBuildId) {
        dmbRepo.deleteById(dormBuildId);
    }

    @Override
    public Response<DormBuild> update (DormBuild newDormBuild) {
        Optional<DormBuild> old = dmbRepo.findById(newDormBuild.getId());
        if (old.isPresent()) {
            DormBuild dormBuild = old.get();
            BeanUtils.copyProperties(dormBuild,newDormBuild);
            dmbRepo.saveAndFlush(dormBuild);
        }
        return Response.failure(ResStatus.USER_NOT_EXIST);
    }
}
