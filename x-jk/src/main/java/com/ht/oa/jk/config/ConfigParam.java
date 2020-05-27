package com.ht.oa.jk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigParam {

    public static String schema;
    public static int port;
    public static String fileSavePrefixPath;

    /**
     * redis
     */
    public static String springRedisHost;
    public static int springRedisPort;
    public static String springRedisPassword;
    public static int springRedisTimeout;

    @Value("${common.schema}")
    public void setSchema(String schema) {
        ConfigParam.schema = schema;
    }

    @Value("${server.port}")
    public void setPort(int port) {
        ConfigParam.port = port;
    }

    @Value("${common.fileSavePrefixPath}")
    public void setFileSavePrefixPath(String fileSavePrefixPath) {
        ConfigParam.fileSavePrefixPath = fileSavePrefixPath;
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
