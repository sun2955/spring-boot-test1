package com.example.demo.service.Impl;

import com.example.demo.dao.HUSERDao;
import com.example.demo.dto.HUSER;
import com.example.demo.service.HelloService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HelloServiceImpl implements HelloService {
    @Resource
    private HUSERDao huserDao;

    @Override
    public void  say(){
        System.out.println("Hello################3");
    }

    @Override
    public HUSER select(Long id){
        HUSER huser =  huserDao.selectByPrimaryKey(id);
        return huser;
    }
}
