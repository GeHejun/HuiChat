package com.ghj.rest.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ghj.rest.entity.Nation;
import com.ghj.rest.mapper.NationDao;
import com.ghj.rest.service.NationService;
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
public class NationServiceImpl extends ServiceImpl<NationDao, Nation> implements NationService {

}
