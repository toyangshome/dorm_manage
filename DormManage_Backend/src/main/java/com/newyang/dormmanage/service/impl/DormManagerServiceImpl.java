package com.newyang.dormmanage.service.impl;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.dao.DormManagerRepository;
import com.newyang.dormmanage.domain.model.DormManager;
import com.newyang.dormmanage.domain.vo.DormManagerListVO;
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

    @Autowired
    public DormManagerServiceImpl (DormManagerRepository dormManagerRepository) {
        this.dmRepo = dormManagerRepository;
    }

    @Override
    public Page<DormManagerListVO> list (PageRequest pageRequest, Example<DormManager> example) {

        return dmRepo
                .findAll(example, pageRequest)
                .map((item) -> new DormManagerListVO()
                        .setDormManId(item.getId())
                        .setSex(item.getSex())
                        .setTel(item.getTel())
                        .setDormBuildName(item.getDormBuild().getDormBuildName())
                        .setUserName(item.getUserName())
                        .setName(item.getName()));
    }

    @Override
    public void delete (Integer id) {
        dmRepo.deleteById(id);
    }

    @Override
    public Response<Void> update (DormManager dormManager) {
        Optional<DormManager> dm = dmRepo.findById(dormManager.getId());
        if (dm.isPresent()) {
            DormManager updateDm = dm.get();
            updateDm.setUserName(dormManager.getUserName());
            updateDm.setName(dormManager.getName());
            updateDm.setSex(dormManager.getSex());
            updateDm.setTel(dormManager.getTel());
            dmRepo.saveAndFlush(updateDm);
            return Response.success();
        }
        return Response.failure(- 1, "目标不存在");
    }
}
