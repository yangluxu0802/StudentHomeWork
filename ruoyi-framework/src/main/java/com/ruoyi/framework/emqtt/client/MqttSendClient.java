package com.ruoyi.framework.emqtt.client;

import com.ruoyi.framework.config.properties.MqttProperties;
import com.ruoyi.framework.emqtt.back.MqttSendCallBack;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * description: 发送消息
 * date: 2022/6/16 16:01
 */
@Component
public class MqttSendClient {

    @Resource
    private MqttSendCallBack mqttSendCallBack;

    @Resource
    private MqttProperties mqttProperties;

    public MqttClient connect() {
        MqttClient client = null;
        try {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            client = new MqttClient(mqttProperties.getHostUrl(), uuid, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(mqttProperties.getUsername());
            options.setPassword(mqttProperties.getPassword().toCharArray());
            options.setConnectionTimeout(mqttProperties.getTimeout());
            options.setKeepAliveInterval(mqttProperties.getKeepAlive());
            options.setCleanSession(true);
            options.setAutomaticReconnect(false);
            try {
                // 设置回调
                client.setCallback(mqttSendCallBack);
                client.connect(options);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }

    /**
     * 发布消息
     * 主题格式： server:report:$orgCode(参数实际使用机构代码)
     *
     * @param retained    是否保留
     * @param pushMessage 消息体
     */
    public void publish(boolean retained, String topic, String pushMessage) {
        MqttMessage message = new MqttMessage();
        message.setQos(mqttProperties.getQos());
        message.setRetained(retained);
        message.setPayload(pushMessage.getBytes());
        MqttClient mqttClient = connect();
        try {
            mqttClient.publish(topic, message);
        } catch (MqttException e) {
            e.printStackTrace();
        } finally {
            disconnect(mqttClient);
            close(mqttClient);
        }
    }

    /**
     * 关闭连接
     *
     * @param mqttClient
     */
    public static void disconnect(MqttClient mqttClient) {
        try {
            if (mqttClient != null) {
                mqttClient.disconnect();
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放资源
     *
     * @param mqttClient
     */
    public static void close(MqttClient mqttClient) {
        try {
            if (mqttClient != null) {
                mqttClient.close();
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}