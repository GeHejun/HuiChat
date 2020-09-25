package com.ghj.rest.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ghj.rest.entity.UserState;
import com.ghj.rest.mapper.UserStateDao;
import com.ghj.rest.service.UserStateService;
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
public class UserStateServiceImpl extends ServiceImpl<UserStateDao, UserState> implements UserStateService {

}