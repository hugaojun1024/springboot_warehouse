package com.zds.springboot.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class TokenUtils {
    /**
     * 生成Token
     * @param userId
     * @return
     */
    public static String getToken(String userId,String sign){
        return JWT.create().withAudience(userId) // 将 user id 保存到 token 里面，作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(),18)) //18小时之后token过期
                .sign(Algorithm.HMAC256(sign)); // 以 password 作为 token 的密钥
    }
    public static String getEmailVerifyToken(String email,String code){
        return JWT.create().withAudience(email) // 将 email地址 保存到 token 里面，作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(),1)) //1小时之后token过期
                .sign(Algorithm.HMAC256(code)); // 以 验证码 作为 token 的密钥
    }
}
