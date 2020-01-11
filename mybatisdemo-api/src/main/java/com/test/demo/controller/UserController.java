package com.test.demo.controller;

import com.test.demo.service.UserService;
import com.test.demo.utils.help.ErrorCode;
import com.test.demo.utils.help.ReturnMesg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import com.test.demo.dao.po.User;
@RestController
@RequestMapping("/userInfo")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/selectAllUser")
    public ReturnMesg selectAllUser() {
        return userService.selectAllUser();
    }

    @GetMapping("/selectUserById")
    public ReturnMesg selectUserById(@PathParam("userId") Integer userId) {
        if (userId == null) {
            return new ReturnMesg(ErrorCode.PARAM_NOT_FOUND);
        } else {
            return userService.selectUserById(userId);
        }
    }

    @PostMapping("/addUser")
    public ReturnMesg addUser(@RequestBody User user) {
        if (user.getUserName() == null || user.getUserPwd() == null || user.getUserNickName() == null || user.getUserPhone() == null) {
            return new ReturnMesg(ErrorCode.PARAM_NOT_FOUND);
        } else {
            return userService.addUser(user.getUserName(), user.getUserPwd(), user.getUserNickName(), user.getUserPhone());
        }
    }

    @PostMapping("/updateUser")
    public ReturnMesg updateUser(@RequestBody User user) {
        if (user.getUserName() == null) {
            return new ReturnMesg(ErrorCode.PARAM_NOT_FOUND);
        } else {
            return userService.updateUser(user.getUserName(), user.getUserPhone(), user.getUserNickName(),
                    user.getUserPwd(), user.getEntryTime());
        }
    }

    @DeleteMapping("deleteUser")
    public ReturnMesg deleteUser(@PathParam("userName") String userName) {
        if (userName == null) {
            return new ReturnMesg(ErrorCode.PARAM_NOT_FOUND);
        } else {
            return userService.deleteUser(userName);
        }
    }

    @PostMapping("userLogin")
    public ReturnMesg userLogin(@RequestBody User user) {
        if (user.getUserName() == null || user.getUserPwd() == null) {
            return new ReturnMesg(ErrorCode.PARAM_NOT_FOUND);
        } else {
            return userService.userLogin(user);
        }
    }
}
