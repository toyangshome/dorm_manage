package com.newyang.dormmanage.dao;

import com.newyang.dormmanage.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}