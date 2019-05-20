package cn.edu.tju.xrs.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

public class JedisDemo {
    public static void main(String args[]){


        //设置IP地址和端口
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.auth("123");
        //Jedis jedis = new Jedis("192.168.145.130",6379);

        //保存数据
        //jedis.set("number","1");
        Transaction transaction = jedis.multi();//开启事务
        for(int i = 300000;i > 0;i--) {
            transaction.incr("number");
        }

        transaction.sadd("wife1");

        for(int i = 300000;i > 0;i--) {
            transaction.incr("number");
        }


        try{
            transaction.exec();
        }catch (Exception e){
            e.printStackTrace();
        }
        String firstWife = jedis.get("number");
        System.out.println(firstWife);

        //释放资源
        jedis.close();


//        /**
//         * 使用连接池的方法连接
//         */
//        //获得连接池配置对象
//        JedisPoolConfig jpconfig = new JedisPoolConfig();
//
//        //设置最大连接数
//        jpconfig.setMaxTotal(30);
//        //设置最大空闲连接数
//        jpconfig.setMaxIdle(10);
//
//        //获得连接池
//        JedisPool jedisPool = new JedisPool(jpconfig,"127.0.0.1",6379);
//        //获得核心对象
//        Jedis jedis = null;
//        try{
//            //通过连接池获得连接
//            jedis = jedisPool.getResource();
//            //设置数据
//            //jedis.set("country","中国");
//            String w = jedis.get("country");
//            System.out.println(w);
//        }catch (Exception e){
//
//        }finally {
//            //释放资源
//            if(jedis != null){
//                jedis.close();
//            }
//            if(jedisPool != null){
//                jedisPool.close();
//            }
//        }

    }
}
