package com.itheima.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {

    @Test
    public void test1(){
        //创建程序与redis的连接
        Jedis jedis = new Jedis("localhost",6379);


       /* jedis.set("myname","武汉");*/
        /*jedis.set("age","10");*/

        //添加字符串并指定过期时间
        jedis.setex("mystring",20,"wuhan");

        jedis.close();

    }
}
