package com.test.demo.dao.mappers;

import com.test.demo.dao.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> queryAllUser();

    User queryUserById(@Param("userId") Integer userId);

    User queryUserByUserName(String userName);

    int queryUserByUserNameAndUserPwd(@Param("userName") String userName, @Param("userPwd") String userPwd);

    int insertUser(User user);

    int deleteUserByUserName(String userName);

    int updateUserByUserName(User user);
}
