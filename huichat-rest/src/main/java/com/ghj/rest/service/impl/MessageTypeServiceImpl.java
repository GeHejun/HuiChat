package com.ghj.rest.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ghj.rest.entity.MessageType;
import com.ghj.rest.mapper.MessageTypeDao;
import com.ghj.rest.service.MessageTypeService;
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
public class MessageTypeServiceImpl extends ServiceImpl<MessageTypeDao, MessageType> implements MessageTypeService {

}
