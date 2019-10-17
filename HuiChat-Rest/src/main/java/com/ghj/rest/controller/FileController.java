package com.ghj.rest.controller;

import com.ghj.common.base.Result;
import com.ghj.rest.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author gehj
 * @date 2019/6/2718:16
 */
@Controller
public class FileController {

    @Resource
    private FileService fileService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Result upload(MultipartFile file)  {
//        String imgUrl = fileService.uploadFile(file);
//        return Result.defaultSuccess(imgUrl);
        return Result.defaultFailure();
    }

}
