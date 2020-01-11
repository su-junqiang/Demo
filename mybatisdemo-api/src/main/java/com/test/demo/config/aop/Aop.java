package com.test.demo.config.aop;

import com.test.demo.service.TokenService;
import com.test.demo.utils.help.ErrorCode;
import com.test.demo.utils.help.ReturnMesg;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
@Component
@Aspect
public class Aop {
    @Autowired
    TokenService tokenService;

    @Pointcut(value = "execution(* com.test.demo.controller.*.*(..))" + "&&! execution(* com.test.demo.controller.UserController.userLogin(..))")
    public void pointCut() {

    }

//    @Before(value = "execution(* com.test.mybatisdemo.controller.UserController.userLogin(..))")
//    public void beforeAdvice() {
//
//        System.out.println("ssssssssssssssssss");
//    }
//
//    @After(value = "execution(* com.test.mybatisdemo.controller.UserController.userLogin(..))")
//    public void afterAdvice() {
//        System.out.println("aaaaaaaaaaaaaaaaaa");
//    }

    @Around("pointCut()")
    public Object handle(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        if (token == null) {
            return new ReturnMesg(ErrorCode.TOKEN_NULL);
        } else if (token.equals("")) {
            return new ReturnMesg(ErrorCode.TOKEN_NULL);
        } else {
            ReturnMesg returnMesg = tokenService.checkToken(token);
            if (returnMesg.getCode() != 200) {
                return returnMesg;
            }
        }
        return proceedingJoinPoint.proceed();
    }
}
