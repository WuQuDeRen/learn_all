package com.learn.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.learn.test.domain.po.PersonDO;

@Repository
public interface PersonDao {

	List<PersonDO> query();

	List<PersonDO> queryById(@Param("id") Integer id);

	List<PersonDO> queryByDynamicId(@Param("id") Integer id);

	int insert(PersonDO personDo);

}
