package com.ruoyi.framework.config;

import com.ruoyi.framework.emqtt.client.MqttAcceptClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class MqttConfig {

    @Resource
    private MqttAcceptClient mqttAcceptClient;

    @Bean
    public MqttAcceptClient getMqttPushClient() {
        mqttAcceptClient.connect();
        return mqttAcceptClient;
    }
}
