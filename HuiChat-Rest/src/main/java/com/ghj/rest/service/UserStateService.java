package com.ghj.rest.service;

import com.ghj.common.dto.response.UserStateResponse;

/**
 * @author GeHejun
 */
public interface UserStateService {
    /**
     * 通过用户状态id查询用户状态
     * @param id
     * @return
     */
    UserStateResponse queryUserStateById(Integer id);

    UserStateResponse queryStateByName(String name);
}
