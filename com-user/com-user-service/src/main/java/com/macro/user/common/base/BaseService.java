package com.macro.user.common.base;

import com.macro.common.entity.mapper.TbUserMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Macro
 * @Date 2020/6/28 14:20
 * @Description  service基类
 */
public class BaseService{

    @Autowired
    protected BaseDao baseDao;

    @Autowired
    protected TbUserMapper tbUserMapper;

}
