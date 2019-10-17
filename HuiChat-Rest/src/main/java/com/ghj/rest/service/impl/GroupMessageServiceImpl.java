package com.ghj.rest.service.impl;

import com.ghj.common.dto.response.GroupMessageResponse;
import com.ghj.common.dto.response.HistoryMessage;
import com.ghj.rest.dao.GroupMessageMapper;
import com.ghj.rest.model.GroupMessage;
import com.ghj.rest.service.GroupMessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gehj
 * @date 2019/6/2812:55
 */
@Service
public class GroupMessageServiceImpl implements GroupMessageService {

    @Resource
    GroupMessageMapper groupMessageMapper;


    @Override
    public void insert(GroupMessage groupMessage) {
        groupMessageMapper.insert(groupMessage);
    }


    @Override
    public HistoryMessage<GroupMessageResponse> queryHistoryGroupMessageListForPage(Integer toGroupId, Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<GroupMessage> groupMessageList = groupMessageMapper.selectGroupMessageByToGroupId(toGroupId);
        PageInfo<GroupMessage> pageInfo = new PageInfo<>(groupMessageList);
        List<GroupMessageResponse> groupMessageResponseList = new ArrayList<>();
        pageInfo.getList().stream().forEach(groupMessage -> {
                    GroupMessageResponse groupMessageResponse = GroupMessageResponse.builder()
                            .id(groupMessage.getId())
                            .content(groupMessage.getContent())
                            .fromUserId(groupMessage.getFromUserId())
                            .sendTime(groupMessage.getSendTime())
                            .toGroupId(groupMessage.getToGroupId()).build();
                    groupMessageResponseList.add(groupMessageResponse);
                }
        );
        HistoryMessage<GroupMessageResponse> historyMessage = new HistoryMessage();
        historyMessage.setPageNum(pageInfo.getPageNum());
        historyMessage.setPageSize(pageInfo.getPageSize());
        historyMessage.setTotal(pageInfo.getTotal());
        historyMessage.setData(groupMessageResponseList);
        return historyMessage;
    }


}
