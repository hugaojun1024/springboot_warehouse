package com.zds.springboot.service;

/**
 * @author hugaojun Email:nat17185546@163.com
 * @create 2023-07-04-[下午 9:49]-周二
 */
public interface IJwtUtil {
    String createToken(String userId);

    boolean verifyToken(String token);

    String getUserIdByToken(String token);

    String getUserNameByToken(String token);
}
