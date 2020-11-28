package com.xzm.springbootjwt.intercept;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.xzm.springbootjwt.until.NologinRequire;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class Interceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在请求之前拦截
        if(!(handler instanceof HandlerMethod))return true;
        System.out.println("preHandle-preHandle-preHandle");
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        //检查是否有NologinRequire注释，有则跳过认证
        if (method.isAnnotationPresent(NologinRequire.class)) {
            NologinRequire nologinRequire = method.getAnnotation(NologinRequire.class);
            return nologinRequire.required();
        }
        // 获取token
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        if (token == null) throw new RuntimeException("无token，请重新登录");
        // 刚刚我们获取token的时候,token保存的是userID,这里就是用来获取用户ID的
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("用户不存在");
        }
        // 这边可以使用userId去数据库里获取密码，我这边就写死了为 123456，因为我生成token的时候sign就是用的123456
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("123456")).build();
        try {
            jwtVerifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            throw new RuntimeException("token非法");
        }
    }
}




