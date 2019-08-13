package com.learn.app.core.service;

import com.learn.system.exception.ServerException;
import com.learn.test.dao.PersonDao;
import com.learn.test.domain.po.PersonDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-08-06 16:26
 */
@Service
public class PersonNestedServiceImpl {

    @Autowired
    private PersonDao personDao;

    public void insert(PersonDO personDo) {
        personDao.insert(personDo);
        throw new ServerException("201","回滚吧，宝宝");
    }
}
