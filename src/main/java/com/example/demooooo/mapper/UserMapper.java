package com.example.demooooo.mapper;

import com.example.demooooo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @Insert("insert into user (phone,password,salt,state,create_time) values (#{phone},#{password},#{salt},#{state},#{creatTime})")
    int insertUser(User user);
    @Select("select user_id,phone,password,salt,nickname,state,create_time,update_time from user where phone =#{phone}")
    User selectUserByPhone(String phone);
}
