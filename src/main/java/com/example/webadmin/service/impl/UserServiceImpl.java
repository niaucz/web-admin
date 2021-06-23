package com.example.webadmin.service.impl;

import com.example.webadmin.entity.User;
import com.example.webadmin.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author niaucz
 * @date 2021-06-23 15:04:31
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {


    @Override
    public User getUserInfoByName(String subject) {
        return null;
    }
}
