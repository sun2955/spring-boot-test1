package com.sun.demo.service;

import com.sun.demo.dto.HUSER;

public interface HelloService {
    void  say();

    HUSER select(Long id);
}
