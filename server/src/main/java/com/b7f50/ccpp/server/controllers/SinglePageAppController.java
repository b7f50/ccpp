package com.b7f50.ccpp.server.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SinglePageAppController {
    @RequestMapping(value = "/**/{path:[^\\.]*}")
    public String redirect() {
        return "forward:/";
    }
}
