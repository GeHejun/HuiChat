package com.ghj.web.controller;

import com.ghj.common.base.Result;
import com.ghj.web.service.IndexService;
import com.ghj.web.vo.MainFrameVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author gehj
 * @version 1.0
 * @description TODO
 * @date 2019/10/24 13:14
 */
@Controller
public class IndexController {

    @Resource
    IndexService indexService;

    @RequestMapping("/index")
    @ResponseBody
    public Result<MainFrameVO> toIndex(Integer id) {
        return Result.defaultSuccess(indexService.queryMainFrame(id));
    }
}
