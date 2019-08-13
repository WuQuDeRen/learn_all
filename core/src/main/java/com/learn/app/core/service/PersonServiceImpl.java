package com.learn.app.core.service;

import com.learn.system.exception.ExceptionAnnotation;
import com.learn.system.exception.ServerException;
import com.learn.test.domain.po.PersonDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonNestedServiceImpl personNestedServiceImpl;


    public void getA() {
        System.out.println("获取实际结果");
    }

    @ExceptionAnnotation
    @Transactional
    @Override
    public void exception(String name, Integer age, ReceiveModel model) {
        PersonDO personDo = new PersonDO();
        personDo.setName("fjjjff323f");
        personDo.setAge(32334);
        personNestedServiceImpl.insert(personDo);
    }
}
