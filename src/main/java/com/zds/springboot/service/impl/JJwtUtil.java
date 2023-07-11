package com.zds.springboot.service.impl;

import com.zds.springboot.service.IJwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Component("jJwtUtil")
@Service
public class JJwtUtil implements IJwtUtil {
    //过期时间
    @Value("${config.jwt.expire}")
    private Long EXPIRE_TIME;

    //密钥
    @Value("${config.jwt.secret}")
    private String SECRET;

    // 生成token（通过用户Id）
    @Override
    public String createToken (String userId){
        Date nowDate = new Date();
        System.out.println(nowDate);

        //在代码中，EXPIRE_TIME乘以1000的目的是将过期时间从秒转换为毫秒。
        // 在Date对象中，时间单位是以毫秒为基准的，因此需要将秒转换为毫秒以便正确设置过期时间。
        Date expireDate = new Date(nowDate.getTime() + EXPIRE_TIME * 1000);

        // 可添加自定义私有的payload的key-value。若与已有的重名会覆盖
        // Map<String, String> claims = new HashMap();
        // claims.put("key1", "value1");

        return Jwts.builder()
                .setAudience(userId)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                // .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, generalKey())
                .compact();
    }

    @Override
    public boolean verifyToken(String token) {
        try {
            System.out.println("verifyToken :" + token);
            Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            // e.printStackTrace();
            return false;
        }
    }

    // 从token中获取用户名
    @Override
    public String getUserNameByToken(String token) {
        return getTokenClaim(token).getSubject();
    }

    // 从token中获取用户id
    @Override
    public String getUserIdByToken(String token) {
        return getTokenClaim(token).getAudience();
    }

    // 获取token中注册信息
    private Claims getTokenClaim (String token) {
        try {
            return Jwts.parser().setSigningKey(generalKey())
                    .parseClaimsJws(token).getBody();
        }catch (Exception e){
            // e.printStackTrace();
            return null;
        }
    }

    // 将secret加密
    private SecretKey generalKey(){
        String stringKey = SECRET;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }
}