package cn.soilove.cache.service;

import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * redis
 *
 * @author: Chen GuoLin
 * @create: 2020-04-14 17:15
 **/
public interface RedisService {

    /**
     * 锁前缀
     */
    String LOCK_KEY_PREFIX = "LOCK:";

    /**
     * 空缓存
     */
    String NULL_VALUE = "NULL_VALUE";

    /**
     * 判断redis#key键是否存在
     * @param key
     * @return
     */
    boolean exists(String key);

    /**
     * 通过redis#key判断value长度
     * @param key
     * @return
     */
    Long strlen(String key);

    /**
     * 新增对象类型
     * @param key
     * @param obj
     */
    void setObj(String key, Object obj);

    /**
     * 新增对象类型 seconds 有效期（单位：s）--正数
     * @param key
     * @param obj
     * @param seconds
     */
    void setObj(String key, Object obj, int seconds);

    /**
     * 新增 value为String类型
     * @param key
     * @param value
     */
    void set(String key, String value);

    /**
     * 新增 value为String类型 seconds 有效期（单位：s）--正数
     * @param key
     * @param value
     * @param seconds
     */
    void set(String key, String value, int seconds);

    /**
     * 新增 value为String类型 seconds 有效期（单位：s）--正数
     * @param key
     * @param value
     * @param seconds
     */
    boolean setnx(String key, String value, int seconds);

    /**
     * 获取key的值
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 获取多个key值
     * @param keys
     * @return
     */
    List<String> mget(String... keys);

    /**
     * 正则批量获取key
     * @param index
     * @param regx
     * @return
     */
    ScanResult<String> scan(int index, String regx);

    /**
     * 正则批量获取key
     * @param regx
     * @return
     */
    ScanResult<String> scan(String regx);

    /**
     * 获取对象类型
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T getObj(String key, Class<T> clazz);

    /**
     * 删除key的有效期
     * @param key
     * @return
     */
    Long persist(String key);

    /**
     * 获取Key的生存时间
     * @param key
     * @return
     */
    Long ttl(String key);

    /**
     * 删除key
     * @param key
     * @return
     */
    Long del(String key);

    /**
     * key值自增1
     * @param key
     * @return
     */
    Long incr(String key);

    /**
     * key值自增指定数值
     * @param key
     * @param increment
     * @return
     */
    Long incrBy(String key,long increment);

    /**
     * hash类型存储数据
     * @param key
     * @param field
     * @param value
     */
    void hset(String key, String field, String value);

    /**
     * hash类型获取数据
     * @param key
     * @param field
     * @return
     */
    String hget(String key, String field);

    /**
     * hash类型删除数据
     * @param key
     * @param field
     * @return
     */
    Long hdel(String key, String field);

    /**
     * hash类型增加数值
     * @param key
     * @param field
     * @param increment
     * @return
     */
    Long hincrBy(String key, String field,long increment);

    /**
     * hash类型 field是否存在
     * @param key
     * @param field
     * @return
     */
    Boolean hexists(String key, String field);

    /**
     * hash类型 所有field
     * @param key
     * @return
     */
    Set<String> hkeys(String key);

    /**
     * hash类型批量添加
     * @param key
     * @param map
     */
    void hmset(String key, Map<String, String> map);

    /**
     * hash类型批量获取
     * @param key
     * @param fields
     * @return
     */
    List<String> hmget(String key, String... fields);

    /**
     * hash获取属性个数
     * @param key
     * @return
     */
    Long hlen(String key);

    /**
     * hash获取全部
     * @param key
     * @return
     */
    Map<String, String> hgetAll(String key);

    /**
     * 添加有序集合元素
     * @param key
     * @param score
     * @param member
     * @return
     */
    Long zadd(String key,double score,String member);

    /**
     * 查询有序集合
     * @param key
     * @param start 开始下标，从0开始
     * @param stop 结束下标，从0开始
     * @return
     */
    Set<String> zrange(String key, Long start, Long stop);

    /**
     * 查询有序集合
     * @param key
     * @param min
     * @param max
     * @return
     */
    Set<String> zrangeByScore(String key, double min, double max);

    /**
     * 删除有序集合元素
     * @param key
     * @param members
     * @return
     */
    Long zrem(String key, String ... members);

    /**
     * 添加集合元素
     * @param key
     * @param member
     * @return
     */
    Long sadd(String key,String member);

    /**
     * 返回集合中的所有元素
     * @param key
     * @param member
     * @return
     */
    Set<String> smembers(String key,String member);

    /**
     * 返回多个集合的交集
     * @param keys
     * @return
     */
    Set<String> sinter(String ... keys);

    /**
     * 移除集合中的元素
     * @param key
     * @param members
     * @return
     */
    Long srem(String key,String ... members);

    /**
     * 移除并返回集合中的一个随机元素
     * @param key
     * @return
     */
    String spop(String key);

    /**
     * 移除并返回集合中的多个随机元素
     * @param key
     * @param count
     * @return
     */
    Set<String> spop(String key,Long count);

    /**
     * 返回集合中的一个随机元素
     * @param key
     * @return
     */
    String srandmember(String key);

    /**
     * 返回集合中的多个随机元素
     * @param key
     * @param count
     * @return
     */
    List<String> srandmember(String key,Integer count);


    /**
     * 从左边入列表
     * @param key
     * @param strings
     */
    void lpush(String key, String... strings);

    /**
     * 从右边入列表
     * @param key
     * @param strings
     */
    void rpush(String key, String... strings);

    /**
     * 返回列表长度
     * @param key
     * @return
     */
    Long llen(String key);

    /**
     * 返回index下标的元素
     * @param key
     * @param index
     * @return
     */
    String lindex(String key, long index);

    /**
     * 获取key列表所有元素
     * @param key
     * @return
     */
    List<String> lrangeAll(String key);

    /**
     * 获取列表指定范围的元素
     * @param key
     * @param start
     * @param end
     * @return
     */
    List<String> lrange(String key, long start, long end);

    /**
     * 弹出列表头部元素
     * @param key
     * @return
     */
    String lpop(String key);

    /**
     * 弹出列表尾部元素
     * @param key
     * @return
     */
    String rpop(String key);

    /**
     * redis阻塞队列 seconds:秒
     * @param key
     * @param seconds
     * @return
     */
    List<String> blpop(String key, int seconds);

    /**
     * 设置key的过期时间
     * @param key
     * @param seconds
     */
    void expire(String key, int seconds);

    /**
     * 分布式锁-锁定，返回值:true-获取锁成功/false-获取锁失败
     * @param key
     * @param seconds
     * @return
     */
    boolean lock(String key, int seconds);

    /**
     * 分布式等待锁
     * @param key
     * @param seconds 持有锁超时秒数
     * @return
     */
    boolean lockWait(String key, int seconds);


    /**
     * 分布式锁-解锁，返回值:true-解除锁成功/false-解除锁失败
     * @param key
     * @return
     */
    boolean unLock(String key);

    /**
     * 使用 Lua 解释器执行脚本
     * @param script
     * @return
     */
    Object eval(String script);

    /**
     * 使用 Lua 解释器执行脚本
     * @param script
     * @param keyCount
     * @param params
     * @return
     */
    Object eval(String script,int keyCount,String ... params);

    /**
     * 使用 Lua 解释器执行脚本
     * @param script
     * @param keys
     * @param args
     * @return
     */
    Object eval(String script,List<String> keys, List<String> args);

    /**
     * 便捷缓存方法
     * @param key
     * @param seconds
     * @param nullSeconds
     * @param supplier
     * @return
     */
    String easyCache(String key, int seconds, int nullSeconds, Supplier<String> supplier);
}
