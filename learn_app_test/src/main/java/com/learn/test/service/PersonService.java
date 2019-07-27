package com.learn.test.service;

import com.learn.test.dao.PersonDao;
import com.learn.test.domain.po.PersonDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-14 02:43
 */
@Service
public class PersonService {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private PersonService2 personService2;

    //  Transaction rolled back because it has been marked as rollback-only
    @Transactional
    public void insert(PersonDO personDo) {
        personDao.insert(personDo);
        try {
            personService2.insert(personDo);
        } catch (Exception e) {
            System.out.println(e);
            throw e;                // 这个 throw 会改变在 285行 TransactionAspectSupport，将 Transaction rolled back异常掩盖掉
        }

    }

    public List<PersonDO> query() {
        return personDao.query();

    }


}
