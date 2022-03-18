package com.newyang.dormmanage.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.codec.json.Jackson2SmileEncoder;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/17 19:24
 */

@SpringBootTest
public class DormManagerRepoTest {
    @Autowired
    DormManagerRepository dormManagerRepository;
    @Test
    public void list() {
        System.out.println(dormManagerRepository.findAll());
    }
}
