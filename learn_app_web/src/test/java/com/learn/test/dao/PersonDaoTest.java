package com.learn.test.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.learn.base.BaseTest;
import com.learn.dao.PersonDao;
import com.learn.domain.PersonDO;

public class PersonDaoTest extends BaseTest {
	
	@Autowired
	private PersonDao personDao;
	@Test
	public void testQuery() {
		List<PersonDO> query = personDao.query();
		System.out.println(query);
	}
}
