package com.dream.work.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/hello")
public class TestController {

    @GetMapping("world")
    public String world() {
        return "Hello World!";
    }

    @GetMapping("test")
    public String test() {
        return "Hello Tester!";
    }
}
