package com.ghj.web.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class MessageVO {

    private String username;

    private String avatar;

    private String id;

    private String type;

    private String content;

    private Long cid;

    private Date timestamp;

    private String fromid;

}
