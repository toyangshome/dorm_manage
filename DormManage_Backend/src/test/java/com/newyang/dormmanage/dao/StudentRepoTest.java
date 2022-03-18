package com.newyang.dormmanage.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author NewYang
 * @email 1013836629@qq.com
 * @date 2022/3/17 18:57
 */

@SpringBootTest
public class StudentRepoTest {
    @Autowired
    StudentRepository studentRepository;
    @Test
    public void list () {
        studentRepository.findAll().forEach(item->{
            System.out.println(item.toString());
        });
    }

}
