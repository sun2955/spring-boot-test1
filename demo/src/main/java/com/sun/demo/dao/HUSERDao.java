package com.sun.demo.dao;

import com.example.demo.dto.HUSER;
import com.example.demo.entiy.HUSER_E;
import org.springframework.stereotype.Repository;

public interface HUSERDao {
    int deleteByPrimaryKey(Long userId);

    int insert(HUSER record);

    int insertSelective(HUSER record);

    HUSER selectByPrimaryKey(Long userId);
    
    HUSER selectByProject(HUSER record);

    int updateByPrimaryKeySelective(HUSER record);

    int updateByPrimaryKeyWithBLOBs(HUSER record);

    int updateByPrimaryKey(HUSER record);
    
    HUSER_E selectUserInfoById(HUSER_E record);
}