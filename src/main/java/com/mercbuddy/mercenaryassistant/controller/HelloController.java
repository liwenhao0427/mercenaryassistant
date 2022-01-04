package com.mercbuddy.mercenaryassistant.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李文浩
 * @date 2021/12/29 9:57
 */

@RestController
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("hello")
    public String hello() {
        return "服务已开启！";
    }
}
