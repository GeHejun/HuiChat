package com.ghj.common.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class HistoryMessage<T> {
    Integer size;
    Integer pageNum;
    Integer pageSize;
    Long total;
    List<T> data;
}
