package com.ghj.web.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HistoryMessageVO {
    Long total;
    Integer pageNum;
    Integer pageSize;
    List<MessageVO> data;
}
