package com.ghj.rest.web;


import com.ghj.common.base.Result;
import com.ghj.common.dto.UserResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author GeHejun
 * @since 2019-10-22
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/{id}")
    @ResponseBody
    public Result<UserResponse> queryUser(@PathVariable("id") Integer id) {

    }
}
