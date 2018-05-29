package com.demo.service;

import com.demo.mapper.UserMapper;
import com.demo.mapper.UserMapper123456;
import com.common.model.User;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService123456 {
    @Autowired
    private UserMapper123456 userMapper;

    @Transactional
    public  void  add(User user){
        userMapper.insert(user);
    }

    public void delete(String key,int id){
        userMapper.delete(key,id);
    }

    public int modify(User user){
       return userMapper.update(user);
    }

    public List<User> selectPage(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userMapper.seletPage();
        return users;
    }

    public User selectById(String key,int id ){

        return userMapper.seletById(key,id);
    }

    public void modify(String key,int userId,String username){

         userMapper.updateByParam(key,userId,username);
    }
}
