package cn.edu.tju.xrs.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo {
    public static void main(String args[]){

        /**
        //设置IP地址和端口
        Jedis jedis = new Jedis("127.0.0.1",6379);
        //Jedis jedis = new Jedis("192.168.145.130",6379);

        //保存数据
        jedis.set("wife1","cocoa");

        String firstWife = jedis.get("wife1");
        System.out.println(firstWife);

        //释放资源
        jedis.close();
         */

        /**
         * 使用连接池的方法连接
         */
        //获得连接池配置对象
        JedisPoolConfig jpconfig = new JedisPoolConfig();

        //设置最大连接数
        jpconfig.setMaxTotal(30);
        //设置最大空闲连接数
        jpconfig.setMaxIdle(10);

        //获得连接池
        JedisPool jedisPool = new JedisPool(jpconfig,"127.0.0.1",6379);
        //获得核心对象
        Jedis jedis = null;
        try{
            //通过连接池获得连接
            jedis = jedisPool.getResource();
            //设置数据
            jedis.set("wife2","estelle");
            String w = jedis.get("wife2");
            System.out.println(w);
        }catch (Exception e){

        }finally {
            //释放资源
            if(jedis != null){
                jedis.close();
            }
            if(jedisPool != null){
                jedisPool.close();
            }
        }

    }
}
