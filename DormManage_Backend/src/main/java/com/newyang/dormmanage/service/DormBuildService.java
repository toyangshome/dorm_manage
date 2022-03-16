package com.newyang.dormmanage.service;

import com.newyang.dormmanage.commons.Response;
import com.newyang.dormmanage.domain.model.DormBuild;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DormBuildService {
    Page<DormBuild> list (PageRequest pageRequest, Example<DormBuild> example);

    void delete (Integer dormBuildId);
    Response<DormBuild> update(DormBuild dormBuild);
}
