package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BJB139 on 2019/7/29.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
       User dbUser = userMapper.findByAccountId(user.getAccount_id());
       if(dbUser == null)
       {
           //插入
           user.setGmt_modified(user.getGmt_create());
           user.setAvatar_url(user.getAvatar_url());
           userMapper.insert(user);

        }else{
           //更新
           dbUser.setGmt_modified(System.currentTimeMillis());
           dbUser.setAvatar_url(user.getAvatar_url());
           dbUser.setName(user.getName());
           dbUser.setToken(user.getToken());
           userMapper.update(dbUser);
       }
    }
}
