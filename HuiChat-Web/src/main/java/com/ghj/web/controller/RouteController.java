package com.ghj.web.controller;

import com.ghj.common.base.Result;
import com.ghj.common.util.RoutingStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/22 18:13
 */
@Controller
@RequestMapping("/route")
public class RouteController {

    @RequestMapping("/bestRoute")
    @ResponseBody
    public Result<String> bestRoute() {
        String[] bestServer = RoutingStrategy.findBestServer();
        return Result.defaultSuccess(bestServer[0] + ":" + bestServer[1]);
    }
}
