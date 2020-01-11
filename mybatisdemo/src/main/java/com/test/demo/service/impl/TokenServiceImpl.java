package com.test.demo.service.impl;

import com.test.demo.service.TokenService;
import com.test.demo.utils.help.ReturnMesg;
import com.test.demo.utils.redis.RedisRepostory;
import com.test.demo.utils.token.TokenUtils;

import com.test.demo.utils.help.ErrorCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    RedisRepostory redisRepo;

    @Override
    public ReturnMesg checkToken(String token) {
        String userName = null;
        Date exp = null;
        Claims jwt;
        try {
            jwt = TokenUtils.deToken(token);
            userName = jwt.getId();
            exp = jwt.getExpiration();
        } catch (Exception e) {
            System.out.println(e);
        }
        if (userName == null) {
            return new ReturnMesg(ErrorCode.TOKEN_CHECK_ERROR);
        }
        String tokenString = redisRepo.get(userName);
        System.out.println("Header:" + token + "\n\t\t\t\t\t\t\t\t\t\t\t\t\t----------------------------" +
                "-------------------\n" + "redis:" + tokenString);
        if (tokenString == null) {
            return new ReturnMesg(ErrorCode.TOKEN_NULL);
        }
        long nowMillis = System.currentTimeMillis();
        Date nodw = new Date(nowMillis);
        if (exp.getTime() - 5 < nodw.getTime() || exp.getTime() + 5 < nodw.getTime()) {
            return new ReturnMesg(ErrorCode.TOKEN_HAS_EXP);
        }
        return new ReturnMesg();
    }
}
