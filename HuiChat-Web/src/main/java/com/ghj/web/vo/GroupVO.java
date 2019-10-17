package com.ghj.web.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GroupVO {
    String id;
    String avatar;
    String groupname;
    String adminId;
}
