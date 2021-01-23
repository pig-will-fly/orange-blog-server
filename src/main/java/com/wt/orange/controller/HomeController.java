package com.wt.orange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p> 主页访问 </p>
 *
 * @author Wang Tao
 * @date 2021-01-12 00:22:59
 */
@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
