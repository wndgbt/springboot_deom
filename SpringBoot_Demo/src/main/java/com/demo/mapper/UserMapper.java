package com.demo.mapper;
import com.common.generic.GenericMapper;
import com.common.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper  extends GenericMapper<Integer, User> {

}