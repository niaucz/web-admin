package com.example.webadmin.controller;

import com.example.webadmin.common.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author niaucz
 * @date 2021-06-23 09:48:14
 */
@RestController
@RequestMapping("home")
public class HomeController {

    @GetMapping("list")
    public R login() {
        return R.ok();
    }

}
