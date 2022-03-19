package com.newyang.dormmanage.service.impl;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.dao.DormBuildRepository;
import com.newyang.dormmanage.dao.DormManagerRepository;
import com.newyang.dormmanage.domain.dto.DormManagerAddDTO;
import com.newyang.dormmanage.domain.dto.DormManagerUpdateDTO;
import com.newyang.dormmanage.domain.model.DormManager;
import com.newyang.dormmanage.domain.vo.DormManagerListVO;
import com.newyang.dormmanage.domain.vo.DormManagerVO;
import com.newyang.dormmanage.service.DormManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/15 23:16
 */
@Service
public class DormManagerServiceImpl implements DormManagerService {
    private final DormManagerRepository dmRepo;
    private final DormBuildRepository dbRepo;

    @Autowired
    public DormManagerServiceImpl (DormManagerRepository dormManagerRepository,
                                   DormBuildRepository dormBuildRepository) {
        this.dmRepo = dormManagerRepository;
        this.dbRepo = dormBuildRepository;
    }

    @Override
    public Page<DormManagerListVO> list (PageRequest pageRequest, Example<DormManager> example) {
        return dmRepo
                .findAll(example, pageRequest)
                .map((item) ->
                        new DormManagerListVO()
                                .setDormManId(item.getId())
                                .setSex(item.getSex())
                                .setTel(item.getTel())
                                .setDormBuildName(dbRepo.queryDormBuildName(item.getDormBuildId()))
                                .setUserName(item.getUserName())
                                .setDormBuildId(item.getDormBuildId())
                                .setName(item.getName())
                );
    }

    @Override
    public void delete (Integer id) {
        dmRepo.deleteById(id);
    }

    @Override
    public Response<DormManagerVO> update (DormManagerUpdateDTO dormManager) {
        Optional<DormManager> dm = dmRepo.findById(dormManager.getDormManId());
        if (dm.isPresent()) {
            DormManager updateDm = dm.get();
            updateDm.setUserName(dormManager.getUserName());
            updateDm.setName(dormManager.getName());
            updateDm.setSex(dormManager.getSex());
            updateDm.setTel(dormManager.getTel());
            updateDm.setDormBuildId(dormManager.getDormBuildId());
            DormManager afterUpdate = dmRepo.saveAndFlush(updateDm);
            return Response.success(new DormManagerVO()
                    .setDormManId(afterUpdate.getDormBuildId())
                    .setSex(afterUpdate.getSex())
                    .setName(afterUpdate.getName())
                    .setTel(afterUpdate.getTel())
                    .setUserName(afterUpdate.getUserName())
                    .setDormBuildName(dbRepo.queryDormBuildName(afterUpdate.getDormBuildId()))
                    .setDormBuildId(afterUpdate.getDormBuildId())
            );
        }
        return Response.failure(- 1, "目标不存在");
    }

    @Override
    public Response<DormManagerVO> add (DormManagerAddDTO params) {
        DormManager queryParams = new DormManager();
        queryParams.setUserName(params.getUserName());
        Optional<DormManager> res = dmRepo.findOne(Example.of(queryParams));
        if (! res.isPresent()) {
            DormManager dormManager = new DormManager();
            dormManager.setName(params.getName());
            dormManager.setTel(params.getTel());
            dormManager.setSex(params.getSex());
            dormManager.setUserName(params.getUserName());
            dormManager.setPassword(params.getPassword());
            dormManager.setDormBuildId(params.getDormBuildId());
            DormManager newDormManager = dmRepo.saveAndFlush(dormManager);

            return Response.success(new DormManagerVO()
                    .setDormManId(newDormManager.getId())
                    .setTel(newDormManager.getTel())
                    .setSex(newDormManager.getSex())
                    .setName(newDormManager.getName())
                    .setUserName(newDormManager.getUserName())
                    .setDormBuildName(dbRepo
                            .queryDormBuildName(newDormManager
                                    .getDormBuildId()
                            )
                    )
            );

        }
        return Response.failure(- 1, "该用户名以存在");
    }
}
