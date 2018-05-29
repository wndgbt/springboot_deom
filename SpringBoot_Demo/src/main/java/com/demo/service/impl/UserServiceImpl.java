package com.demo.service.impl;
import com.common.generic.AbstractGenericService;
import com.common.generic.GenericMapper;
import com.demo.mapper.UserMapper;
import com.common.model.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends AbstractGenericService<Integer, User> implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    protected GenericMapper<Integer, User> getMapper() {
        return userMapper;
    }
}
