package com.newyang.dormmanage.service.impl;

import com.newyang.dormmanage.commons.ResStatus;
import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.dao.DormBuildRepository;
import com.newyang.dormmanage.dao.DormManagerRepository;
import com.newyang.dormmanage.domain.model.DormBuild;
import com.newyang.dormmanage.domain.model.DormManager;
import com.newyang.dormmanage.domain.vo.DormBuildListVO;
import com.newyang.dormmanage.domain.vo.DormManagerListVO;
import com.newyang.dormmanage.service.DormBuildService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/16 0:26
 */

@Service
public class DormBuildServiceImpl implements DormBuildService {
    private final DormBuildRepository dmbRepo;
    private final DormManagerRepository dormManagerRepo;

    @Autowired
    public DormBuildServiceImpl (DormBuildRepository dormBuildRepository,
                                 DormManagerRepository dormManagerRepository) {
        this.dmbRepo = dormBuildRepository;
        this.dormManagerRepo = dormManagerRepository;
    }

    @Override
    public Page<DormBuild> list (PageRequest pageRequest, Example<DormBuild> example) {
        return dmbRepo.findAll(example, pageRequest);
    }

    @Override
    public List<DormBuildListVO> listAll () {
        return dmbRepo
                .findAll()
                .stream()
                .map(item -> new DormBuildListVO()
                        .setDormBuildId(item.getId())
                        .setDormBuildName(item.getDormBuildName()))
                .collect(Collectors.toList());
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
            BeanUtils.copyProperties(dormBuild, newDormBuild);
            dmbRepo.saveAndFlush(dormBuild);
        }
        return Response.failure(ResStatus.USER_NOT_EXIST);
    }

    @Override
    public DormBuild add (String dormBuildName, String detail) {
        DormBuild queryParams = new DormBuild();
        queryParams.setDormBuildName(dormBuildName);
        dmbRepo.findOne(Example.of(queryParams)).ifPresent((item) -> {
            throw new RuntimeException("已存在该楼栋");
        });
        DormBuild saveDormBuild = new DormBuild();
        saveDormBuild.setDormBuildName(dormBuildName);
        saveDormBuild.setDetail(detail);
        return dmbRepo.saveAndFlush(saveDormBuild);
    }

    @Override
    public Response<List<DormManagerListVO>> manager (Integer dormBuildId) {
        DormManager queryParams = new DormManager();
        queryParams.setDormBuildId(dormBuildId);
        List<DormManagerListVO> res = dormManagerRepo.findAll(Example.of(queryParams)).stream().map(item -> new DormManagerListVO()
                .setDormManId(item.getId())
                .setSex(item.getSex())
                .setTel(item.getTel())
                .setDormBuildName(dmbRepo.queryDormBuildName(item.getDormBuildId()))
                .setUserName(item.getUserName())
                .setDormBuildId(item.getDormBuildId())
                .setName(item.getName())).collect(Collectors.toList());
        if (! res.isEmpty()) {
            return Response.success(res);
        }
        return Response.failure(- 1, "该栋楼没有管理员");
    }

    @Override
    public Response<DormBuild> update (Integer id, String dormBuildName, String detail) {
        Optional<DormBuild> res = dmbRepo.findById(id);
        if (res.isPresent()) {
            DormBuild dormBuild = res.get();
            dormBuild.setDormBuildName(dormBuildName);
            dormBuild.setDetail(detail);
            return Response.success(dmbRepo.saveAndFlush(dormBuild));
        }
        return Response.failure(- 1, "没有该楼栋");
    }
}
