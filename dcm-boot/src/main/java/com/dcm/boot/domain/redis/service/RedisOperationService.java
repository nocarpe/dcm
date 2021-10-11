package com.dcm.boot.domain.redis.service;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface RedisOperationService {

    /**
     * 删除缓存
     * (String)
     */
    boolean remove(String cacheKey);

    /**
     * 设置缓存
     * (String)
     */
    <T> boolean setValue(String cacheKey, T objValue, long expiration);

    /**
     * 设置缓存
     * (String)
     */
    <T> boolean setValueBySecond(String cacheKey, T objValue, long expiration);

    /**
     * 设置缓存
     * (String)
     */
    <T> boolean setValueByDay(String cacheKey, T objValue, long expiration);

    /**
     * 获取缓存
     * (String)
     */
    <T> T getValue(String cacheKey, Class<T> tClass);

    /**
     * 设置缓存
     * (list)
     */
    <T> boolean putList(String cacheKey, T objValue);

    <T> boolean putListAll(String cacheKey, Collection<T> values);

    /**
     * 从缓存列表中删除对象
     */
    <T> boolean removeFromList(String cacheKey, T objValue);

    /**
     * 获取缓存
     * (list)
     */
    <T> List<T> getList(String cacheKey);

    /**
     * 删除key
     */
    boolean removeKey(String cacheKey);


    /**
     * 判断key是否存在
     */
    boolean existKey(String cacheKey);

    /**
     * 批量获取缓存
     * (String)
     */
    <T> List<T> batchGetValue(List<String> list);

    /**
     * 向Hash结构中放入一个属性
     */
    void hSet(String key, String hashKey, Object value);

    /**
     * 获取hash结构中的一个属性
     */
    <T> T hGet(String key, String hashKey);

    /**
     * 获取hash结构中的集合属性
     * @return
     */
    <T> List<T> hMultiGet(String key, Collection hashKey);

    /**
     * 判断变量中是否有指定的map键
     */
    boolean hasKey(String key, String hashKey);

    /**
     * 直接获取整个Hash结构
     */
    <T> Map<String, T> hGetAll(String key);

    /**
     * 直接设置整个Hash结构
     */
    <T> void hSetAll(String key, Map<String, T> map);

    /**
     * 删除Hash结构中的属性
     */
    void hDel(String key, Object... hashKey);

    /**
     * 添加list
     */
    <T> void insertListByDay(String key, List<T> list, long expiration);

    Long increment(String redisKey);

    Long increment(String redisKey, long value);

    /**
     * 批量删除key
     */
    Long removeKeys(List<String> keys);

    /**
     * 批量设置缓存 默认不会超时
     */
    <T> void batchSetValue(Map<String,T> map);

    /**
     * 设置过期时间的锁
     * @param lockKey
     * @param timeout
     * @param unit
     * @return
     */
    boolean lock(String lockKey, long timeout, TimeUnit unit);

    /**
     * 设置 zSet
     */
    boolean zSetAdd(String cacheKey, Object objValue, Double score);

    /**
     * 删除 zSet value
     */
    boolean zSetRemove(String cacheKey, Object... values);

    /**
     * 顺序获取zSet 所有value
     */
    Set<String> zSetGetAllAsc(String cacheKey);

    /**
     * 设置过期key
     *
     * @param key key
     */
    boolean expire(String key, long timeoutSeconds);


    <T> List<T> hGetValues(String key);

    void executeIncrByPipelined(String key, Map<Long, Long> map, long seconds);

    <K, V> Map<K, V> hGetMap(String key);

    <K, V> Map<K, V> hMGetMap(String key, List<K> fileds);

    <T> void hSetMap(String key, Map<Long, T> map);

    void hDelMap(String key, Object... hashKey);

    <T> void batchSetValueBySecond(Map<String, T> map, Long seconds);

    Long getExpire(String cacheKey);

}