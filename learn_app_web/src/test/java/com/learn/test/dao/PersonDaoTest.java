package com.learn.test.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
//import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.learn.base.BaseTest;

public class PersonDaoTest extends BaseTest {
	
    public static Logger logger = LogManager.getLogger(PersonDaoTest.class);
	
	@Autowired
	private PersonDao personDao;
	@Test
	public void testQuery() {
		logger.info("aa【{}】{}{}{}{}{}{}{}{}{}{}{}", 12, 23,23,23,23,23,23,23,23,23,23,23,23);
//		List<PersonDO> query = personDao.query();
//		System.out.println(query);
	}
}
