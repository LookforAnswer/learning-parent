package com.qxy.redisson;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

/**
 * @Description: 类信息描述
 * @Author: qinxiaoyun001@lianjia.com
 * @Date: 2020/6/6 11:11 下午
 * @Version: 1.0
 */
public class RedissonTest {

    public static void main(String[] args) {
        String key = "key1";
        RedissonClient client = RedissonClientFactory.getClient();

        RMap<Object,Object> map = client.getMap(key);
        map.fastPut("test1", "value1");
        map.fastPut("test2", "value2");
        map.fastPut("test3", "value3");

        client.shutdown();
    }
}
