package com.itheima.test;

import mall.utils.JedisUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolTest {

    @Test
    public void test1(){
        Jedis jedis = JedisUtil.getJedis();
        System.out.print(jedis);
    }

    @Test
    public void test2(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(5);
        config.setMaxTotal(20);

        JedisPool jedisPool = new JedisPool(config,"127.0.0.1",6379);

        Jedis jedis = jedisPool.getResource();

        jedis.set("name","erbao");
        String name = jedis.get("name");
        System.out.print(name);
    }

}
