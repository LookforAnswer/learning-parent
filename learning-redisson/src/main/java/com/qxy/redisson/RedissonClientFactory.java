package com.qxy.redisson;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

/**
 * @Description: 类信息描述
 * @Author: qinxiaoyun001@lianjia.com
 * @Date: 2020/6/6 11:07 下午
 * @Version: 1.0
 */
public class RedissonClientFactory {

    private static Config config;

    static {
        config = new Config();
        config.setTransportMode(TransportMode.NIO);
        // 如果 redis 启动了单台，需要使用 useSingleServer 方式
        // 如果 redis 启动了单台，使用 useSingleServer 方法，会报 异常
        config.useSingleServer().setAddress("redis://localhost:6379");
    }

    public static RedissonClient getClient(){
        return Redisson.create(config);
    }
}
