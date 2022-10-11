package com.appsdeveloperblog.ws.clients.socialloginwebclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String displayIndexPage() {
        return "index";
    }
}
