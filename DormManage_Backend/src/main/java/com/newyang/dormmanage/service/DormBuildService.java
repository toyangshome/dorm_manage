package com.newyang.dormmanage.service;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.domain.model.DormBuild;
import com.newyang.dormmanage.domain.vo.DormBuildListVO;
import com.newyang.dormmanage.domain.vo.DormManagerListVO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface DormBuildService {
    Page<DormBuild> list (PageRequest pageRequest, Example<DormBuild> example);
    List<DormBuildListVO> listAll();
    void delete (Integer dormBuildId);
    Response<DormBuild> update(DormBuild dormBuild);
    DormBuild add (String dormBuildName, String detail);

    Response<List<DormManagerListVO>> manager (Integer dormBuildId);

    Response<DormBuild> update (Integer id, String dormBuildName, String detail);
}
