package com.sun.demo.service.Impl;

import com.sun.demo.dao.HUSERDao;
import com.sun.demo.dto.HUSER;
import com.sun.demo.service.HelloService;
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
