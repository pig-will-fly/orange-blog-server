package com.wt.orange.service.impl;

import com.wt.orange.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p> 用户相关操作 </p>
 *
 * @author Wang Tao
 * @date 2021-02-04 20:21:01
 */
@Service
public class UserServiceImpl {
    @Autowired
    private UserMapper userMapper;

}
