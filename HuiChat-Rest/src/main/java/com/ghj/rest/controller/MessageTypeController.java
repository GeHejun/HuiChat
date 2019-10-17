package com.ghj.rest.controller;

import com.ghj.common.base.Result;
import com.ghj.rest.service.MessageTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/messageType")
public class MessageTypeController {

    @Resource
    MessageTypeService messageTypeService;

    @RequestMapping("/queryMessageTypeIdByName")
    @ResponseBody
    public Result<Integer> queryMessageTypeIdByName(@RequestParam("name") String name) {
        return Result.defaultSuccess(messageTypeService.queryMessageTypeByName(name));
    }

    @RequestMapping("/queryMessageTypeNameById")
    @ResponseBody
    public Result<String> queryMessageTypeNameById(@RequestParam("id") Integer id) {
        return Result.defaultSuccess(messageTypeService.queryMessageTypeNameById(id));
    }
}
