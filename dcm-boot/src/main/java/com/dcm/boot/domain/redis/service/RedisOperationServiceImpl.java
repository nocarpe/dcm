package com.dcm.boot.domain.redis.service;


public class RedisOperationServiceImpl implements RedisOperationService {
/*

    @Resource(name = "redisClusterTemplate")
    private RedisTemplate redisClusterTemplate;



    @Override
    public boolean remove(String cacheKey) {
        return redisClusterTemplate.delete(cacheKey);
    }

    @Override
    public <T> boolean setValue(String cacheKey, T objValue, long expiration) {
        if (Objects.equals(0L, expiration)) {
            redisClusterTemplate.opsForValue().set(cacheKey, objValue);
        } else {
            redisClusterTemplate.opsForValue().set(cacheKey, objValue, expiration, TimeUnit.MINUTES);
        }
        return true;
    }

    */
    /**
     * 设置缓存
     * (String)
     *//*

    @Override
    public <T> boolean setValueBySecond(String cacheKey, T objValue, long expiration) {
        if (Objects.equals(0L, expiration)) {
            redisClusterTemplate.opsForValue().set(cacheKey, objValue);
        } else {
            redisClusterTemplate.opsForValue().set(cacheKey, objValue, expiration, TimeUnit.SECONDS);
        }
        return true;
    }

    */
    /**
     * 设置缓存
     * (String)
     *//*

    @Override
    public <T> boolean setValueByDay(String cacheKey, T objValue, long expiration) {
        if (Objects.equals(0L, expiration)) {
            redisClusterTemplate.opsForValue().set(cacheKey, objValue);
        } else {
            redisClusterTemplate.opsForValue().set(cacheKey, objValue, expiration, TimeUnit.DAYS);
        }
        return false;
    }

    @Override
    public <T> T getValue(String cacheKey, Class<T> tClass) {
        return (T) redisClusterTemplate.opsForValue().get(cacheKey);
    }

    @Override
    public <T> boolean putList(String cacheKey, T objValue) {
        log.info("putList cacheKey:{},objValue:{}", cacheKey, JsonUtils.toJson(objValue));
        redisClusterTemplate.opsForList().rightPush(cacheKey, objValue);
        return true;
    }

    @Override
    public <T> boolean putListAll(String cacheKey, Collection<T> values) {
        redisClusterTemplate.opsForList().rightPushAll(cacheKey, values);
        return true;
    }

    @Override
    public <T> boolean removeFromList(String cacheKey, T objValue) {
        log.info("removeFromList cacheKey:{},objValue:{}", cacheKey, JsonUtils.toJson(objValue));
        redisClusterTemplate.opsForList().remove(cacheKey, 0L, objValue);
        return true;
    }


    @Override
    public <T> List<T> getList(String cacheKey) {
        Long size = redisClusterTemplate.opsForList().size(cacheKey);
        if (Objects.equals(0L, size)) {
            return ImmutableList.of();
        }
        return redisClusterTemplate.opsForList().range(cacheKey, 0, size - 1);
    }


    @Override
    public void executeIncrByPipelined(String keyPrefix, Map<Long, Long> map, long seconds) {
        redisClusterTemplate.executePipelined((RedisCallback<Object>) connection -> {
            if (!redisClusterTemplate.hasKey(keyPrefix)) {
                redisClusterTemplate.expire(keyPrefix, seconds, TimeUnit.SECONDS);
            }
            map.forEach((filed, value) -> {
                redisClusterTemplate.opsForHash().increment(keyPrefix, String.valueOf(filed), Optional.ofNullable(value).orElse(0L));
            });
            return null;
        });
    }


    @Override
    public boolean removeKey(String cacheKey) {
        return redisClusterTemplate.delete(cacheKey);
    }

    */
    /**
     * 判断key是否存在
     *//*

    @Override
    public boolean existKey(String cacheKey) {
        return redisClusterTemplate.hasKey(cacheKey);
    }

    @Override
    public <T> List<T> batchGetValue(List<String> list) {
        return redisClusterTemplate.opsForValue().multiGet(list);
    }

    @Override
    public void hSet(String key, String hashKey, Object value) {
        redisClusterTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public <T> T hGet(String key, String hashKey) {
        return (T) redisClusterTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public <T> List<T> hMultiGet(String key, Collection hashKey) {
        return (List<T>) redisClusterTemplate.opsForHash().multiGet(key, hashKey);
    }

    @Override
    public boolean hasKey(String key, String hashKey) {
        return redisClusterTemplate.opsForHash().hasKey(key, hashKey);
    }

    @Override
    public <T> Map<String, T> hGetAll(String key) {
        return redisClusterTemplate.opsForHash().entries(key);
    }

    @Override
    public <T> void hSetAll(String key, Map<String, T> map) {
        redisClusterTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public void hDel(String key, Object... hashKey) {
        redisClusterTemplate.opsForHash().delete(key, hashKey);
    }

    */
    /**
     * 添加list
     *//*

    @Override
    public <T> void insertListByDay(String key, List<T> list, long expiration) {
        redisClusterTemplate.opsForList().leftPush(key, list);
        //设置过期时间
        redisClusterTemplate.expire(key, expiration, TimeUnit.DAYS);
    }

    */
    /**
     * 自增
     *//*

    @Override
    public Long increment(String redisKey) {
        return redisClusterTemplate.opsForValue().increment(redisKey);
    }

    @Override
    public Long increment(String redisKey, long value) {
        return redisClusterTemplate.opsForValue().increment(redisKey, value);
    }

    @Override
    public <T> void batchSetValue(Map<String, T> map) {
        if (MapUtils.isEmpty(map)) {
            return;
        }
        redisClusterTemplate.opsForValue().multiSet(map);
    }

    @Override
    public Long removeKeys(List<String> keys) {
        return redisClusterTemplate.delete(keys);
    }

    */
    /**
     * 设置过期时间的锁
     *//*

    @Override
    public boolean lock(String lockKey, long timeout, TimeUnit unit) {
        return redisClusterTemplate.opsForValue().setIfAbsent(lockKey, UUID.randomUUID(), timeout, unit);
    }


    @Override
    public boolean zSetAdd(String cacheKey, Object objValue, Double score) {
        // 注意：redis的score的值为double，长度超过16位后，会变成科学计数法，丢失精度更多
        boolean addResult = redisClusterTemplate.opsForZSet().add(cacheKey, objValue, score);
        if (addResult) {
            return true;
        }
        // 对于SortedSet，如果k-v存在，只修改了score，ZADD返回false，这里获取score，比较优先级，来计算是否设置成功
        Double priority = redisClusterTemplate.opsForZSet().score(cacheKey, objValue);
        if (null != priority) {
            addResult = score.equals(priority);
        }
        return addResult;
    }

    */
    /**
     * 删除 zSet
     *//*

    @Override
    public boolean zSetRemove(String cacheKey, Object... values) {
        Long remove = redisClusterTemplate.opsForZSet().remove(cacheKey, values);
        return values.length == remove.intValue();
    }

    */
    /**
     * 顺序获取zSet 所有value
     *//*

    @Override
    public Set<String> zSetGetAllAsc(String cacheKey) {
        return redisClusterTemplate.opsForZSet().rangeByLex(cacheKey, RedisZSetCommands.Range.unbounded());
    }

    */
    /**
     * 设置过期key
     *
     * @param key key
     *//*

    @Override
    public boolean expire(String key, long seconds) {
        return redisClusterTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    @Override
    public <T> List<T> hGetValues(String key) {
        return redisClusterTemplate.opsForHash().values(key);
    }
    @Override
    public <K, V> Map<K, V> hGetMap(String key) {
        RedisSerializer hashKeySerializer = redisClusterTemplateForAllHash.getHashKeySerializer();
        RedisSerializer hashValueSerializer = redisClusterTemplateForAllHash.getHashValueSerializer();
        byte[] rawKey = redisClusterTemplateForAllHash.getStringSerializer().serialize(key);
        Map<byte[], byte[]> entries = (Map<byte[], byte[]>) redisClusterTemplate
            .execute(connection -> connection.hGetAll(rawKey), true);
        return entries != null ? deserializeHashMap(entries, hashKeySerializer, hashValueSerializer)
            : Collections.emptyMap();
    }


    @Override
    public <K, V> Map<K, V> hMGetMap(String key, List<K> fileds) {
        if (CollectionUtil.isEmpty(fileds)) {
            return ImmutableMap.of();
        }
        List<V> values = redisClusterTemplateForAllHash.opsForHash().multiGet(key, fileds);
        Map<K, V> resultMap = new HashMap<>();
        for (int i = 0; i < fileds.size(); i++) {
            if (Objects.nonNull(values.get(i))) {
                resultMap.put(fileds.get(i), values.get(i));
            }
        }
        return resultMap;
    }

    @Override
    public <T> void hSetMap(String key, Map<Long, T> map) {
        redisClusterTemplateForAllHash.opsForHash().putAll(key, map);
    }

    @Override
    public void hDelMap(String key, Object... hashKey) {
        redisClusterTemplateForAllHash.opsForHash().delete(key, hashKey);
    }
    @Override
    public <T> void batchSetValueBySecond(Map<String, T> map, Long seconds) {
        redisClusterTemplate.executePipelined((RedisCallback<Object>) connection -> {
            map.forEach((filed, value) -> {
                if (Objects.equals(0L, seconds)) {
                    redisClusterTemplate.opsForValue().set(filed, value);
                } else {
                    redisClusterTemplate.opsForValue().set(filed, value, seconds, TimeUnit.SECONDS);
                }
            });
            return null;
        });
    }


    @Override
    public Long getExpire(String cacheKey) {
        //此方法返回单位为秒过期时长
        return redisClusterTemplate.opsForValue().getOperations().getExpire(cacheKey);
    }

    private <K, V> Map<K, V> deserializeHashMap(Map<byte[], byte[]> entries, RedisSerializer hashKeySerializer,
        RedisSerializer hashValueSerializer) {
        if (entries == null) {
            return null;
        }
        Map<K, V> map = new LinkedHashMap<>(entries.size());
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = (GenericJackson2JsonRedisSerializer) hashKeySerializer;

        for (Map.Entry<byte[], byte[]> entry : entries.entrySet()) {
            map.put((K) genericJackson2JsonRedisSerializer.deserialize(entry.getKey(), Long.class),
                (V) hashValueSerializer.deserialize(entry.getValue()));
        }
        return map;
    }*/
}