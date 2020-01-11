package com.test.demo.service;

import com.test.demo.dao.po.User;
import com.test.demo.utils.help.ReturnMesg;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    ReturnMesg selectAllUser();

    ReturnMesg addUser(String userName, String userPwd, String userNickName, Integer userPhone);

    ReturnMesg updateUser(String userName, Integer userPhone, String userNickName, String userPwd, String entryTime);

    ReturnMesg deleteUser(String userName);

    ReturnMesg selectUserById(Integer userId);

    ReturnMesg userLogin(User user);
}
