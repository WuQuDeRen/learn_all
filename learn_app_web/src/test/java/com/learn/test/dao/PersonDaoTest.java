package com.learn.test.dao;


import com.github.pagehelper.PageHelper;
import com.learn.base.BaseTest;
import com.learn.test.domain.po.PersonDO;
import com.learn.test.service.PersonService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.collections.Maps;

import java.util.List;
import java.util.Map;

public class PersonDaoTest extends BaseTest {

//    public static Logger logger = LogManager.getLogger(PersonDaoTest.class);

	public static org.slf4j.Logger logger = LoggerFactory.getLogger(PersonDaoTest.class);


	@Autowired
	private PersonDao personDao;

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Test
	public void testQuery() {
		//PageHelper.startPage(1, 10);
		logger.info("aa【{}】{}{}{}{}{}{}{}{}{}{}{}", 12, 23,23,23,23,23,23,23,23,23,23,23,23);
		List<PersonDO> query = personDao.query();
		System.out.println(query);
	}

	@Test
	public void testInsert() {
		PersonDO person =  new PersonDO();
		person.setAge(18);
		person.setName("jifei");
		int result = personDao.insert(person);
		System.out.println(result);

	}
	private SqlSessionTemplate template;

	@Test
	public void test() {
		logger.info("ddddddddddd");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Map<String, Object> params = Maps.newHashMap();
		params.put("sql", "insert into t_person(name, age) values(#{name}, #{age})");
		params.put("name", "fff");
		params.put("age", 12);
		int insert = sqlSession.insert("com.learn.test.dao.PersonDao.testInsert", params);
		System.out.println(insert);
	}



	@Autowired
	private PersonService personService;

	@Test
	public  void testTransaction() {
		PersonDO person =  new PersonDO();
		person.setAge(12);
		person.setName("jifei2");

		personService.insert(person);
	}

	@Test
	public void testPersonService() {
		List<PersonDO> query = personService.query();
		System.out.println(query);
	}







}
