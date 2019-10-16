package com.ghj.web.controller;

import com.ghj.common.base.Result;
import com.ghj.common.util.RoutingStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/16 11:16
 */
@RestController
@RequestMapping("/route")
public class RouteController {

    @RequestMapping("/bestServer")
    public Result<String> findBestServer() {
        String[] node = RoutingStrategy.findBestServer();
        return Result.defaultSuccess(node[0] + ":" + node[1]);
    }
}
