package com.example.webadmin.controller;

import com.example.webadmin.common.R;
import com.example.webadmin.form.LoginForm;
import com.example.webadmin.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author niaucz
 * @date 2021-06-22 14:07:24
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 注册
     *
     * @param loginForm
     * @return
     */
    @PostMapping(value = "/register")
    public R register(@RequestBody LoginForm loginForm) {
//        if () {
//            return R.ok();
//        }
//        return R.error();
        return null;
    }

    /**
     * 登陆
     *
     * @param loginForm
     * @return
     */
    @PostMapping("login")
    public R login(@RequestBody LoginForm loginForm) {
        //查询用户根据用户名和密码
        //if 存在
        //生成token
        String token = jwtUtils.generateToken(loginForm.getUsername());
        return null;
    }

}
