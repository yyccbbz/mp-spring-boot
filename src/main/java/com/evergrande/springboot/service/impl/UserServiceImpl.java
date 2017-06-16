package com.evergrande.springboot.service.impl;

import com.evergrande.springboot.entity.User;
import com.evergrande.springboot.mapper.UserMapper;
import com.evergrande.springboot.service.IUserService;
import com.evergrande.springboot.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author CuiCan
 * @since 2017-06-10
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {
	
}
