package com.test.demo.service;

import com.test.demo.utils.help.ReturnMesg;
import org.springframework.stereotype.Component;

@Component
public interface TokenService {
    ReturnMesg checkToken(String token);
}
