package com.ghj.web.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class MemberVO {
    List<UserVO> list;
}
