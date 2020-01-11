package com.test.demo.service.impl;

import com.test.demo.dao.mappers.UserMapper;
import com.test.demo.dao.po.User;
import com.test.demo.service.UserService;
import com.test.demo.utils.help.ErrorCode;
import com.test.demo.utils.help.ReturnMesg;
import com.test.demo.utils.redis.RedisRepostory;
import com.test.demo.utils.token.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    RedisRepostory redisRepo;

    @Override
    public ReturnMesg selectAllUser() {
        List<User> users = userMapper.queryAllUser();
//        System.out.println(users.size());
//        for (int i = 0; i < users.size(); i++) {
//
//        }
        return new ReturnMesg(users);
    }

    @Override
    public ReturnMesg addUser(String userName, String userPwd, String userNickName, Integer userPhone) {
        int statu = 0;
        try {
            User user = new User();
            user.setUserName(userName);
            user.setUserPwd(userPwd);
            user.setUserNickName(userNickName);
            user.setUserPhone(userPhone);
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String entryTime = date.format(new Date());
            String role = "ROLE_USER";
            String manageLocate = "NULL";
            user.setManageLocate(manageLocate);
            user.setEntryTime(entryTime);
            user.setRole(role);
            statu = userMapper.insertUser(user);
        } catch (Exception e) {
            System.out.println(e);
        }
        if (statu > 0) {
            return new ReturnMesg();
        } else {
            return new ReturnMesg(ErrorCode.USER_ADD_ERROR);
        }
    }

    @Override
    public ReturnMesg updateUser(String userName, Integer userPhone, String userNickName, String userPwd, String entryTime) {
        int statu = 0;
        try {
            User user = new User();
            user.setUserName(userName);
            user.setUserPwd(userPwd);
            user.setUserNickName(userNickName);
            user.setUserPhone(userPhone);
            user.setEntryTime(entryTime);
            statu = userMapper.updateUserByUserName(user);
        } catch (Exception e) {
            System.out.println(e);
        }
        if (statu > 0) {
            return new ReturnMesg();
        } else {
            return new ReturnMesg(ErrorCode.USER_UPDATE_ERROR);
        }
    }

    @Override
    public ReturnMesg deleteUser(String userName) {
        int statu = 0;
        try {
            statu = userMapper.deleteUserByUserName(userName);
            System.out.println(statu);
        } catch (Exception e) {
            System.out.println(e);
        }
        if (statu > 0) {
            return new ReturnMesg();
        } else {
            return new ReturnMesg(ErrorCode.USER_DELETE_ERROR);
        }
    }

    @Override
    public ReturnMesg selectUserById(Integer userId) {
        User user = null;
        try {
            user = userMapper.queryUserById(userId);
        } catch (Exception e) {
            System.out.println(e);
        }
        if (user != null) {
            return new ReturnMesg(user);
        } else {
            return new ReturnMesg(ErrorCode.USER_NOT_FOUND);
        }
    }

    @Override
    public ReturnMesg userLogin(User user) {
        String userName = user.getUserName();
        String userPwd = user.getUserPwd();
        System.out.println(userName + userPwd);
        int statu = userMapper.queryUserByUserNameAndUserPwd(userName, userPwd);
        if (statu == 0) {
            return new ReturnMesg(ErrorCode.UNDIFIND_ACCOUNT_PWD);
        } else {
            String tokenString = redisRepo.get(userName);
            if (tokenString == null) {
                tokenString = TokenUtils.creatJwtToken(user.getUserName());
                redisRepo.add(userName, tokenString, 15L);
            }else {

            }
            System.out.println(tokenString);
            return new ReturnMesg(200, "登录成功", tokenString);
        }
    }

}
