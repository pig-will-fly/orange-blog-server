package com.wt.orange.controller;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p> 主页访问 </p>
 *
 * @author Wang Tao
 * @date 2021-01-12 00:22:59
 */
@Controller
public class HomeController extends Model {
    @GetMapping("/index")
    @ResponseBody
    public String index() {
        return "index";
    }
}
