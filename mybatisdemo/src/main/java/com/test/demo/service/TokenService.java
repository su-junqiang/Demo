package com.test.demo.service;

import com.test.demo.utils.help.ReturnMesg;

public interface TokenService {
    ReturnMesg checkToken(String token);
}
