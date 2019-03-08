package com.sun.demo.dao;

import com.sun.demo.entiy.StudentInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// 包含jpa内置的一些的方法
public interface StudentInfoDao extends JpaRepository<StudentInfoEntity, Long> {


}
