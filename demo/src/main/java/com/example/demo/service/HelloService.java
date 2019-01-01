package com.example.demo.service;

import com.example.demo.dto.HUSER;

public interface HelloService {
    void  say();

    HUSER select(Long id);
}
