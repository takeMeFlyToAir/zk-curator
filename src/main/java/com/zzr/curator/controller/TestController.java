package com.zzr.curator.controller;

import ch.qos.logback.classic.Logger;
import com.zzr.curator.config.ZKConfig;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaozhirong on 2019/5/5.
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    Logger logger = (Logger) LoggerFactory.getLogger(TestController.class);

    @Autowired
    private ZKConfig zkConfig;

    @RequestMapping(value = "/test")
    public String test(){
        return zkConfig.getConnectionString();
    }

    @RequestMapping("/log")
    public String logbackLevel() throws Exception {
        Logger logger = (Logger) LoggerFactory.getLogger("root");
        String levelStr = logger.getLevel().levelStr;
        return levelStr;
    }



}
