package com.ghj.rest.service.impl;

import com.ghj.rest.entity.User;
import com.ghj.rest.mapper.UserDao;
import com.ghj.rest.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GeHejun
 * @since 2019-10-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}
