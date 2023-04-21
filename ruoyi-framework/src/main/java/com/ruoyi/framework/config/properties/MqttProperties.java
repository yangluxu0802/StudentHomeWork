package com.ruoyi.framework.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties("mqtt")
public class MqttProperties {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 连接地址
     */
    private String hostUrl;

    /**
     * 客户端Id，同一台服务器下，不允许出现重复的客户端id
     */
    private String clientId;

    /**
     * 默认连接主题
     */
    private String topic;

    /**
     * 超时时间
     */
    private int timeout;

    /**
     * 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端
     * 发送个消息判断客户端是否在线，但这个方法并没有重连的机制
     */
    private int keepAlive;

    /**
     * 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连
     * 接记录，这里设置为true表示每次连接到服务器都以新的身份连接
     */
    private Boolean cleanSession;

    /**
     * 是否断线重连
     */
    private Boolean reconnect;

    /**
     * 连接方式
     */
    private Integer qos;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(int keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Boolean getCleanSession() {
        return cleanSession;
    }

    public void setCleanSession(Boolean cleanSession) {
        this.cleanSession = cleanSession;
    }

    public Boolean getReconnect() {
        return reconnect;
    }

    public void setReconnect(Boolean reconnect) {
        this.reconnect = reconnect;
    }

    public Integer getQos() {
        return qos;
    }

    public void setQos(Integer qos) {
        this.qos = qos;
    }
}