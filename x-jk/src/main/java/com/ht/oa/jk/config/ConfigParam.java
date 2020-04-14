package com.ht.oa.jk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigParam {

    public static int runMode;

    public static String schema;
    public static String domain;
    public static int port;
    public static String fileSavePrefixPath;

    /**
     * 反向代理配置
     */
    public static int isProxy;
    public static int proxyPort;

    /**
     * redis
     */
    public static String springRedisHost;
    public static int springRedisPort;
    public static String springRedisPassword;
    public static int springRedisTimeout;

    @Value("${run.mode}")
    public void setRunMode(int runMode) {
        ConfigParam.runMode = runMode;
    }

    @Value("${common.schema}")
    public void setSchema(String schema) {
        ConfigParam.schema = schema;
    }

    @Value("${common.domain}")
    public void setDomain(String domain) {
        ConfigParam.domain = domain;
    }

    @Value("${server.port}")
    public void setPort(int port) {
        ConfigParam.port = port;
    }

    @Value("${common.fileSavePrefixPath}")
    public void setFileSavePrefixPath(String fileSavePrefixPath) {
        ConfigParam.fileSavePrefixPath = fileSavePrefixPath;
    }

    @Value("${common.isProxy}")
    public void setIsProxy(int isProxy) {
        ConfigParam.isProxy = isProxy;
    }

    @Value("${common.proxyPort}")
    public void setProxyPort(int proxyPort) {
        ConfigParam.proxyPort = proxyPort;
    }

    @Value("${spring.redis.host}")
    public void setSpringRedisHost(String springRedisHost) {
        ConfigParam.springRedisHost = springRedisHost;
    }

    @Value("${spring.redis.port}")
    public void setSpringRedisPort(int springRedisPort) {
        ConfigParam.springRedisPort = springRedisPort;
    }

    @Value("${spring.redis.password}")
    public void setSpringRedisPassword(String springRedisPassword) {
        ConfigParam.springRedisPassword = springRedisPassword;
    }

    @Value("${spring.redis.timeout}")
    public void setSpringRedisTimeout(int springRedisTimeout) {
        ConfigParam.springRedisTimeout = springRedisTimeout;
    }

}
