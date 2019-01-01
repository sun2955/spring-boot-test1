package com.example.demo.service;

import com.example.demo.dao.HUSERDao;
import com.example.demo.dto.HUSER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HelloService {

    @Resource
    private HUSERDao huserDao;

    public void  say(){
        System.out.println("Hello################3");
    }

    public HUSER  select(Long id){
        HUSER huser =  huserDao.selectByPrimaryKey(id);
        return huser;
    }
}
