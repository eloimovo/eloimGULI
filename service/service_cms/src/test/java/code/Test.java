package code;


import redis.clients.jedis.Jedis;

import java.util.List;

public class Test {


    @org.junit.Test
    public void test(){
       Jedis jedis= new Jedis("192.168.42.128");
        System.out.println(jedis);
        System.out.println(jedis.ping());
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        System.out.println(jedis.llen("site-list"));
        List<String> list = jedis.lrange("site-list", 0 ,jedis.llen("site-list"));
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }
    }
}
