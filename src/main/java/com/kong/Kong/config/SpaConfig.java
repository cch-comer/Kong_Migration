package com.kong.Kong.config;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaConfig implements ErrorController {

    @GetMapping(value = "/error")
    public String spaConfig() {
        return "forward:/index.html";
    }
}
