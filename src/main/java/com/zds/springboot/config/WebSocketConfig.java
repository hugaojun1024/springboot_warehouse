package com.zds.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author hugaojun Email:nat17185546@163.com
 * @create 2023-06-14-[上午 10:31]-周三
 */
//开启WebSocket的支持，并把该类注入到spring容器中
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
