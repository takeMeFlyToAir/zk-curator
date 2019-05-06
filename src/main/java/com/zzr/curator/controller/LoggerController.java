package com.zzr.curator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaozhirong on 2019/5/6.
 */
@RestController
@RequestMapping(value = "/log")
public class LoggerController {

    Logger logger = LoggerFactory.getLogger(LoggerController.class);

    @RequestMapping(value = "/log")
    public String log(){
        logger.info("===============info========");
        logger.error("===============error==========");
        return "logger";
    }

}
