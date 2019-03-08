package com.sun.demo.base.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sun.demo.base.Exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JWTUtil {
    private static final Logger log = LoggerFactory.getLogger(JWTUtil.class);

    // 过期时间5分钟
    private static final long EXPIRE_TIME = 5 * 60 * 1000;

    //固定秘钥
    private static final byte[] secret= {'h','z','l','a','p','i','s','e','c','r','e','t'};

    /**
     * 生成签名,5min后过期
     *
     * @param username 用户名
     * @return 加密的token
     */
    public static String sign(String username) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create()
                .withClaim("username", username)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @return 是否正确
     */
    public static boolean verify(String token,String username) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();

            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
            throw new BaseException("鉴权失败,message="+exception.getMessage());
        }

        return true;
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("username").asString();
    }

}
