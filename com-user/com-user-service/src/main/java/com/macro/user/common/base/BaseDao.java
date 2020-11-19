package com.macro.user.common.base;


import com.common.core.utils.StringUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;


/**
 * @auther Macro
 * @date 2019-06-25 13:23
 * @description 基类
 */
@Repository("baseDao")
public class BaseDao extends SqlSessionDaoSupport{

	@Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

	/**
	 * 查询对象，只返回一条数据，有多条数据会有异常
	 */
	public <T> T selectOne(String sqlId, Object obj) {
		if(!StringUtil.isEmpty(obj))
		{
			return getSqlSession().selectOne(sqlId, obj);
		}
		else
		{ 
			return getSqlSession().selectOne(sqlId);
		}
	}

	/**
	 * 查询对象集合
	 */
	public <E> List<E> selectList(String sqlId, Object obj) {
		if(!StringUtil.isEmpty(obj))
		{
			return getSqlSession().selectList(sqlId, obj);
		}
		else
		{
			return getSqlSession().selectList(sqlId);
		}
	}

	
	/**
	 * 插入对象
	 */
	public Serializable addNewObject(String sqlId, Object obj) {
		if(!StringUtil.isEmpty(obj))
		{
			return getSqlSession().insert(sqlId, obj);
		}
		else
		{
			return null;
		}

	}
	
	/**
	 * 批量插入对象
	 */
	public Serializable batchListObject(String sqlId, List<Object> obj) {
		if(!obj.isEmpty())
		{
			return getSqlSession().insert(sqlId, obj);
		}
		else
		{
			return null;
		}

	}
     
	/**
	 * 更新对象
	 */
	public boolean updateObject(String sqlId, Object obj) {
		if(!StringUtil.isEmpty(obj))
		{   
			getSqlSession().update(sqlId, obj);
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 批量更新对象
	 */
	public int updateListObject(String sqlId, List<Object> obj) {
		if(!obj.isEmpty())
		{   
			return getSqlSession().update(sqlId, obj);
		}
		else
		{
			return 0;
		}
	}
	
	/**
	 * 删除对象
	 */
	public int deleteObject(String sqlId, Object obj){
		if(!StringUtil.isEmpty(obj))
		{   
			int i = getSqlSession().delete(sqlId, obj);
			return i;
		}
		else
		{   
			int i = getSqlSession().delete(sqlId);
			return i;
		}
	}


}
