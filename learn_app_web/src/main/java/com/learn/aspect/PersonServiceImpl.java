package com.learn.aspect;

import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    public void getA() {
        System.out.println("获取实际结果");
    }
}
