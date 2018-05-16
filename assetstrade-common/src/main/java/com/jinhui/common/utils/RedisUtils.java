package com.jinhui.common.utils;


import com.alibaba.fastjson.JSON;
import com.jinhui.common.entity.po.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 */

public class RedisUtils {

    private static RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>) SpringContextUtils.getBean("redisTemplate");

    private static ValueOperations<String, String> valueOperations = (ValueOperations<String, String>) SpringContextUtils.getBean("valueOperations");


    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**
     * 一个小时
     */
    public final static long ONE_HOUR_EXPIRE = 60 * 60 * 1;
    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1;


    /**
     * 保存对象成json字符串，设置缓存时间
     *
     * @param key
     * @param value
     * @param expire
     */
    public static void set(String key, Object value, long expire) {
        valueOperations.set(key, toJson(value));
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 保存对象成json字符串，设置缓存时间, 同时放入threadLocal
     *
     * @param key
     * @param value
     * @param expire
     */
    public static void setLocalUser(String key, Object value, long expire) {
        valueOperations.set(key, toJson(value));
        threadLocal.set(toJson(value));
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public static User getLocalUser() {
        return fromJson(RedisUtils.threadLocal.get(), User.class);
    }

    public static User getRedisUser(HttpServletRequest req) {
        return RedisUtils.get(req.getHeader("token"), User.class, RedisUtils.ONE_HOUR_EXPIRE);
    }

    /**
     * 每次线程结束后，要清理保存的user信息
     */
    public static void removeLocalUser() {
        threadLocal.remove();
    }

    /**
     * 保存对象成json字符串，不设置缓存时间
     *
     * @param key
     * @param value
     */
    public static void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }


    /**
     * 获取java对象,获取之后，刷新缓存时间
     *
     * @param key
     * @param clazz
     * @param expire
     * @param <T>
     * @return
     */
    public static <T> T get(String key, Class<T> clazz, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value, clazz);
    }

    /**
     * 获取java对象,获取之后，不刷新缓存时间
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    /**
     * 获取保存的字符串，获取之后刷新缓存时间
     *
     * @param key
     * @param expire
     * @return
     */
    public static String get(String key, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    public static String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public static void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * Object转成JSON数据
     */
    private static String toJson(Object object) {
        if (object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String) {
            return String.valueOf(object);
        }
        return JSON.toJSONString(object);
    }

    /**
     * JSON数据，转成Object
     */
    private static <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

}
