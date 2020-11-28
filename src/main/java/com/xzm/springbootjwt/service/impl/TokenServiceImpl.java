package com.xzm.springbootjwt.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.xzm.springbootjwt.service.TokenService;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String getToken(Integer userId, String password) {


        return JWT.create().withAudience(userId.toString()).sign(Algorithm.HMAC256(password));
    }
}
