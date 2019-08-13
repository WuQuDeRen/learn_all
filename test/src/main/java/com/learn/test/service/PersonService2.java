package com.learn.test.service;

import com.learn.test.dao.PersonDao;
import com.learn.test.domain.po.PersonDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-14 02:44
 */
@Service
public class PersonService2 {

    @Autowired
    private PersonDao personDao;

    @Transactional
    public void insert(PersonDO personDo) {
        personDao.insert(personDo);
        throw new RuntimeException("测试异常");
    }
}
