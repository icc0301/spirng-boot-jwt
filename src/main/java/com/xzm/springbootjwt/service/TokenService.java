package com.xzm.springbootjwt.service;

public interface TokenService {

    String getToken(Integer userId, String password);
}
