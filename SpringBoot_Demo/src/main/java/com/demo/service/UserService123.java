package com.demo.service;
import com.common.generic.AbstractGenericService;
import com.common.generic.GenericMapper;
import com.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService123{
    @Autowired
    private UserMapper userMapper;

}
