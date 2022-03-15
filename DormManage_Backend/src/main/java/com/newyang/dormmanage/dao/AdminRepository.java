package com.newyang.dormmanage.dao;

import com.newyang.dormmanage.domain.model.Admin;
import org.springframework.data.repository.CrudRepository;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/15 17:42
 */

public interface AdminRepository extends CrudRepository<Admin,Integer> {
    Admin findByAdminId(int id);
}
