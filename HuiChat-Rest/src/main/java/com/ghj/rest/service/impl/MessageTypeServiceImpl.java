package com.ghj.rest.service.impl;

import com.ghj.rest.dao.MessageTypeMapper;
import com.ghj.rest.service.MessageTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/9/6 15:46
 */
@Service
public class MessageTypeServiceImpl implements MessageTypeService {

    @Resource
    MessageTypeMapper messageTypeMapper;

    @Override
    public Integer queryMessageTypeByName(String name) {
        return messageTypeMapper.selectByName(name).getId();
    }

    @Override
    public String queryMessageTypeNameById(Integer id) {
        return messageTypeMapper.selectByPrimaryKey(id).getName();
    }
}
