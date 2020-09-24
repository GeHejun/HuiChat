package com.ghj.rest.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ghj.rest.entity.City;
import com.ghj.rest.mapper.CityDao;
import com.ghj.rest.service.CityService;
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
public class CityServiceImpl extends ServiceImpl<CityDao, City> implements CityService {

}
