package com.ht.oa.jk.utils.cache;

import com.ht.oa.jk.config.ConfigParam;
import com.ht.oa.jk.utils.common.StringUtils;
import com.ht.oa.jk.utils.constant.AppConst;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

public class RedisCacheFactory {

    private static String addr = ConfigParam.springRedisHost;
    private static int port = ConfigParam.springRedisPort;
    private static String auth = ConfigParam.springRedisPassword;
    private static int timeout = ConfigParam.springRedisTimeout; //超时时间
    private static int max_active = 1024;//最大连接数
    private static int max_idle = 200; //最大的空闲连接
    private static int max_wait = 5000;//最大等待时间
    private static boolean test_on_borrow = true;//空闲时检查有效性
    private static Map<Integer, JedisPool> jedisPoolMap = new HashMap<>();

    /**
     * 初始化连接
     * args 表示数据库，如果不传则使用默认值=0
     */
    private synchronized static JedisPool getJedisPool(int dbIndex) {
        JedisPool jedisPool = jedisPoolMap.get(dbIndex);
        if (jedisPool != null) {
            return jedisPool;
        }
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(max_active);
        config.setMaxIdle(max_idle);
        config.setMaxWaitMillis(max_wait);
        config.setTestOnBorrow(test_on_borrow);
        jedisPool = new JedisPool(config, addr, port, timeout, auth, dbIndex);
        jedisPoolMap.put(dbIndex, jedisPool);
        return jedisPool;
    }

    /**
     * 获取jedis实例
     */
    private synchronized static Jedis getJedis(int... args) {
        try {
            int dbIndex = 0;
            if (args != null && args.length > 0) {
                dbIndex = args[0];
            }
            JedisPool jedisPool = getJedisPool(dbIndex);
            if (jedisPool != null) {
                return jedisPool.getResource();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 释放jedis资源
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * 设置 String
     */
    public static void setString(String key, String value, int... args) {
        Jedis jedis = getJedis(args);
        try {
            value = StringUtils.isBlank(value) ? "" : value;
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    /**
     * 设置 过期时间
     */
    public static void setString(String key, int seconds, String value, int... args) {
        Jedis jedis = getJedis(args);
        try {
            value = StringUtils.isBlank(value) ? "" : value;
            jedis.setex(key, seconds, value);
        } catch (Exception e) {
        } finally {
            jedis.close();
        }
    }

    /**
     * 获取String值
     */
    public static String getString(String key, int... args) {
        Jedis jedis = getJedis(args);
        try {
            if (jedis == null || !jedis.exists(key)) {
                return null;
            }
            String val = jedis.get(key);
            return val;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return null;
    }

    /**
     * 删除key
     */
    public static void removeKey(String key, int... args) {
        Jedis jedis = getJedis(args);
        jedis.del(key);
    }

    /**
     * 判断key是否存在
     */
    public static boolean existKey(String key, int... args) {
        Jedis jedis = getJedis(args);
        return jedis.exists(key);
    }

    public static void lpush(byte[] key, byte[] value, int... args) {
        Jedis jedis = getJedis(args);
        try {
            String tmp = new String(value, AppConst.CHARSET);
            tmp = StringUtils.isBlank(tmp) ? "" : tmp;
            jedis.lpush(key, tmp.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    public static String rpop(byte[] key, int... args) {
        Jedis jedis = getJedis(args);
        try {
            if (jedis == null || !jedis.exists(key)) {
                return null;
            }
            byte[] str = jedis.rpop(key);
            return new String(str, AppConst.CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return null;
    }

}
