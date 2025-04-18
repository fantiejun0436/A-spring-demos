package tech.pdai.springboot.redis.utiles.lock.common.utiles;


import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RBuckets;
import org.redisson.api.RKeys;
import org.redisson.api.RList;
import org.redisson.api.RLock;
import org.redisson.api.RMapCache;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tech.pdai.springboot.redis.utiles.lock.exception.RedisIdempotentOperationException;
import tech.pdai.springboot.redis.utiles.lock.exception.RedisLockAcquisitionException;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;


/**
 * redis工具类
 * @author zuochao_wang
 */
@Component
public class RedisUtils {

    private static final Logger log = LoggerFactory.getLogger(RedisUtils.class);

    /**
     * redis锁前缀
     */
    public static final String SYS_LOCK_FLAG = "MY_LOCK";

    /**
     * 默认缓存时间
     */
    private static final Long DEFAULT_EXPIRED = 32000L;

    private static final String IDEMPOTENT_LOCK_KEY = "idempotent_lock";
    private static final String IDEMPOTENT_KEY = "idempotent";
    /**
     * 幂等键过期时间
     */
    private static final int IDEMPOTENT_EXPIRE_HOURS = 24;

    /**
     * 自动装配redisson client对象
     */

    private final RedissonClient redissonClient ;

    public RedisUtils(RedissonClient redissonClient){
        this.redissonClient = redissonClient;
    }

    /**
     * 用于操作key
     * @return RKeys 对象
     */
    public RKeys getKeys() {
        return redissonClient.getKeys();
    }
    /**
     * 移除缓存
     *
     * @param key
     */
    public void delete(String key) {
        redissonClient.getBucket(key).delete();
    }

    /**
     * 获取getBuckets 对象
     *
     * @return RBuckets 对象
     */
    public RBuckets getBuckets() {
        return redissonClient.getBuckets();
    }

    /**
     * 获取指定的桶对象
     *
     * @return RBucket<T> 对象
     */
    public <T> RBucket<T> getBucket(String key) {
        return redissonClient.getBucket(key);
    }

    /**
     * 读取缓存中的字符串，永久有效
     *
     * @param key 缓存key
     * @return 字符串
     */
    public String getStr(String key) {
        RBucket<String> bucket = redissonClient.getBucket(key,StringCodec.INSTANCE);
        return bucket.get();
    }

    /**
     * 缓存字符串
     *
     * @param key
     * @param value
     */
    public void setStr(String key, String value) {
        RBucket<String> bucket = redissonClient.getBucket(key,StringCodec.INSTANCE);
        bucket.set(value);
    }



    /**
     * 缓存带过期时间的字符串(单位为秒)
     *
     * @param key     缓存key
     * @param value   缓存值
     * @param expired 缓存过期时间，long类型，必须传值
     */
    public void setStr(String key, String value, long expired) {
        RBucket<String> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
        bucket.set(value, expired <= 0L ? DEFAULT_EXPIRED : expired, TimeUnit.SECONDS);
    }

    /**
     * 缓存带过期时间的字符串(单位为小时)
     * @param key
     * @param value
     * @param expired
     */
    public void setStr(String key, String value, int expired) {
        RBucket<String> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
        bucket.set(value, expired <= 0 ? 24 : expired, TimeUnit.HOURS);
    }

    /**
     * string 操作，如果不存在则写入缓存（string方式，不带有redisson的格式信息）
     *
     * @param key     缓存key
     * @param value   缓存值
     * @param expired 缓存过期时间
     */
    public Boolean setIfAbsent(String key, String value, long expired) {
        RBucket<String> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
        return bucket.trySet(value, expired <= 0L ? DEFAULT_EXPIRED : expired, TimeUnit.SECONDS);
    }

    /**
     * 如果不存在则写入缓存（string方式，不带有redisson的格式信息），永久保存
     *
     * @param key   缓存key
     * @param value 缓存值
     */
    public Boolean setIfAbsent(String key, String value) {
        RBucket<String> bucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
        return bucket.trySet(value);
    }

    /**
     * 判断缓存是否存在
     *
     * @param key
     * @return true 存在
     */
    public Boolean isExists(String key) {
        return redissonClient.getBucket(key).isExists();
    }

    /**
     * 获取RList对象
     *
     * @param key RList的key
     * @return RList对象
     */
    public <T> RList<T> getList(String key) {
        return redissonClient.getList(key);
    }

    /**
     * 获取RMapCache对象
     *
     * @param key
     * @return RMapCache对象
     */
    public <K, V> RMapCache<K, V> getMap(String key) {
        return redissonClient.getMapCache(key);
    }

    /**
     * 获取RSET对象
     *
     * @param key
     * @return RSET对象
     */
    public <T> RSet<T> getSet(String key) {
        return redissonClient.getSet(key);
    }

    /**
     * 获取RScoredSortedSet对象
     *
     * @param key
     * @param <T>
     * @return RScoredSortedSet对象
     */
    public <T> RScoredSortedSet<T> getScoredSortedSet(String key) {
        return redissonClient.getScoredSortedSet(key);
    }

    /**
     * 加锁
     *
     * @param lockName    锁名  相同的key表示相同的锁，建议针对不同的业务使用不同的key
     * @return
     */
    public boolean getLock(String lockName) {
        String key = getLockKey(lockName);
        //获取锁对象
        RLock lock = redissonClient.getLock(key);
        boolean lockAcquired = lock.tryLock();
        try {
            return lockAcquired;
        } finally {
            if (lockAcquired) {
                lock.unlock();
            }
        }
    }

    /**
     * 释放锁，建议放在 finally里面
     *
     * @param lockName 锁名称
     */
    public void unlock(String lockName) {
        String key = getLockKey(lockName);
        //获取锁对象
        RLock lock = redissonClient.getLock(key);
        // 释放锁,判断要解锁的key是否已被锁定并且是否被当前线程保持
        if (lock.isLocked() && lock.isHeldByCurrentThread()) {
            lock.unlock();
            log.info("Release Redis lock successfully，key:{}", key);
        }
    }

    /**
     * 对锁的key添加系统标识前缀
     *
     * @return
     */
    public String getLockKey(String key) {
        return RedisUtils.SYS_LOCK_FLAG + ConstantEnum.KEY_SPLIT.getValue() + key;
    }

    /**
     * 设置计数器的初始值
     *
     * @param counterName 计数器的名称
     * @param initialValue 初始值
     */
    public void setCounterInitialValue(String counterName, long initialValue) {
        RAtomicLong counter = redissonClient.getAtomicLong(counterName);
        counter.set(initialValue);
    }

    /**
     * 工具方法：实现计数器的自减操作
     *
     * @param counterName 计数器的名称
     * @return 自减后的计数器值
     */
    public long decrementCounter(String counterName) {
        // 使用Redisson的RAtomicLong来操作计数器
        RAtomicLong counter = redissonClient.getAtomicLong(counterName);
        // 执行自减操作并获取自减后的值
        return counter.decrementAndGet();
    }

    /**
     * 工具方法：实现计数器的自增操作
     *
     * @param counterName
     * @return
     */
    public long incrementCounter(String counterName) {
        // 使用Redisson的RAtomicLong来操作计数器
        RAtomicLong counter = redissonClient.getAtomicLong(counterName);
        // 执行自增操作并获取自增后的值
        return counter.incrementAndGet();
    }

    /**
     * 公共的幂等性方法
     * @param operationName 操作名称
     * @param requestId 业务ID
     * @param consumer 实际的业务操作
     */
    public <T extends Object> void performIdempotentOperation(String operationName, T requestId, Consumer<T> consumer) {
        String lockKey = IDEMPOTENT_LOCK_KEY + ConstantEnum.KEY_SPLIT.getValue() + operationName + ConstantEnum.KEY_SPLIT.getValue() + requestId;
        String key = IDEMPOTENT_KEY + ConstantEnum.KEY_SPLIT.getValue() + operationName + ConstantEnum.KEY_SPLIT.getValue() + requestId;
        RLock lock = redissonClient.getLock(lockKey);
        boolean isLocked = false;
        try {
            // 尝试获取锁，并设置超时和等待时间
            isLocked = lock.tryLock();
            if (isLocked && getStr(key) == null) {
                consumer.accept(requestId);
                setStr(key, key, 24);
            } else {
                log.info("Failed to acquire lock for idempotent operation: {}", lockKey);
            }
        } catch (Exception e){
            log.error("performIdempotentOperation is error", e);
        }finally {
            if (isLocked) {
                lock.unlock();
            }
        }
    }

    /**
     * 基于业务ID加锁并执行操作（支持幂等性）
     * @param operationName 操作名称
     * @param requestId 业务ID
     * @param consumer 业务逻辑
     * @throws RedisLockAcquisitionException 获取锁失败
     * @throws RedisIdempotentOperationException 幂等性冲突
     */
    public <T> void executeWithLockId(String operationName, T requestId, Consumer<T> consumer) {
        String key = getStrKey(operationName, requestId);
        String lockKey = IDEMPOTENT_LOCK_KEY + ConstantEnum.KEY_SPLIT.getValue() + operationName + ConstantEnum.KEY_SPLIT.getValue() + requestId;
        RLock lock = redissonClient.getLock(lockKey);

        try {
            // 尝试获取锁（等待3秒，锁持有30秒后自动释放）
            boolean isLockAcquired = lock.tryLock();
            if (!isLockAcquired) {
                throw new RedisLockAcquisitionException("Failed to acquire lock for operation: " + operationName);
            }
            // 检查幂等性（如果已存在则直接返回）
            if (getStr(key) != null) {
                throw new RedisIdempotentOperationException("Operation already processed for id: " + requestId);
            }
            // 标记为已处理（保留24小时）
            setStr(key, key, IDEMPOTENT_EXPIRE_HOURS);
            log.info("executeWithLockId the operationName:{}, requestId:{}", operationName, requestId );
            // 执行业务逻辑
            consumer.accept(requestId);
        } catch (RedisLockAcquisitionException | RedisIdempotentOperationException e) {
            log.error("executeWithLockId is error", e);
            throw new RedisLockAcquisitionException("Lock acquisition interrupted", e);
        } finally {
            // 确保锁被释放，无论发生什么异常
            lock.unlock();
        }
    }

    /**
     * 基于业务ID进行解锁
     * @param operationName 操作名称
     * @param requestId 业务ID
     */
    public <T> void executeWithUnLockId(String operationName, T requestId){
        String key = getStrKey(operationName, requestId);

        // 检查幂等性（如果不存在或为空则直接返回）
        if (getStr(key) == null || getBucket(key) == null || !isExists(key)) {
            log.info("Key '{}' does not exist or is null. Skipping cache removal.", key);
            return;
        }

        // 去除缓存
        boolean unLockStatus = getBucket(key).delete();
        log.info("executeWithUnLockId the operationName:{}, requestId:{}, unLockStatus:{}", operationName, requestId, unLockStatus);
    }

    public  <T> String getStrKey(String operationName, T requestId) {
        return IDEMPOTENT_KEY + ConstantEnum.KEY_SPLIT.getValue() + operationName + ConstantEnum.KEY_SPLIT.getValue() + requestId;
    }

    /**
     * 获取key,通过自定义分隔符拼接
     * @param key
     * @return
     */
    public String getKey(String separator , String... key) {
        if(key == null || key.length == 0 || separator == null){
            return null;
        }
        if(key.length == 1){
            return key[0];
        }
        return Arrays.stream(key).collect(Collectors.joining(separator));
    }

    /**
     * 幂等性方法
     * @param key 操作名称
     * @param threadId 业务ID
     */
    public String performIdempotentOperationExternal(String key, long threadId ,String businessSystemCode) {
        String lockKey = IDEMPOTENT_LOCK_KEY + ConstantEnum.KEY_SPLIT.getValue() + key + ConstantEnum.KEY_SPLIT.getValue() + threadId;
        RLock lock = redissonClient.getLock(lockKey);
        boolean isLocked = false;
        try {
            // 尝试获取锁，并设置超时和等待时间
            isLocked = lock.tryLock();
            if (isLocked && getStr(key) == null) {
                setStr(key, String.valueOf(threadId), 1);
                return key;
            } else {
                log.info("key is be used: {}", key);
                Thread.sleep(1000);
                key = DateUtil.get14timeStr(businessSystemCode);
                log.info("new key is : {}", key);
                return performIdempotentOperationExternal(key,threadId,businessSystemCode);
            }
        } catch (Exception e){
            log.error("performIdempotentOperationExternal is error", e);
        }finally {
            if (isLocked) {
                lock.unlock();
            }
        }
        return key;
    }

    /**
     * 检查操作是否已被锁定
     *
     * @param operationName 操作名称
     * @param requestId 请求标识
     * @return 如果操作已被锁定，则返回true；否则返回false
     */
    public boolean isLocked(String operationName, String requestId) {
        // 构造锁的唯一键值
        String lockKey = IDEMPOTENT_LOCK_KEY + ConstantEnum.KEY_SPLIT.getValue() + operationName + ConstantEnum.KEY_SPLIT.getValue() + requestId;
        // 构造操作的唯一键值
        String key = IDEMPOTENT_KEY + ConstantEnum.KEY_SPLIT.getValue() + operationName + ConstantEnum.KEY_SPLIT.getValue() + requestId;
        // 获取Redis分布式锁
        RLock lock = redissonClient.getLock(lockKey);
        // 标记是否获取到锁
        boolean isLocked = false;
        try {
            // 尝试获取锁，并设置超时和等待时间
            isLocked = lock.tryLock();
            // 如果获取到锁且操作对应的标识不存在，则创建标识并返回true，表示操作未执行过
            if (isLocked && getStr(key) == null) {
                setStr(key, key, 24);
                return true;
            } else {
                // 如果未获取到锁或操作对应的标识已存在，记录日志并返回false
                log.info("Failed to acquire lock for idempotent operation: {}", lockKey);
            }
        } catch (Exception e){
            // 如果在等待获取锁的过程中被中断，记录错误日志
            log.error("isLocked is error", e);
        }finally {
            // 如果获取到锁，释放锁
            if (isLocked) {
                lock.unlock();
            }
        }
        // 如果未获取到锁或操作对应的标识已存在，返回false
        return false;
    }


    /**
     * 检查操作是否已被锁定
     *
     * @param operationName 操作名称
     * @param inputKey 请求标识
     * @return 如果操作已被锁定，则返回true；否则返回false
     */
    public boolean isLocked(String operationName, String inputKey, String inputVal, Integer hour) {
        // 构造锁的唯一键值
        String lockKey = IDEMPOTENT_LOCK_KEY + ConstantEnum.KEY_SPLIT.getValue() + operationName + ConstantEnum.KEY_SPLIT.getValue() + inputKey;
        // 构造操作的唯一键值
        String key = IDEMPOTENT_KEY + ConstantEnum.KEY_SPLIT.getValue() + operationName + ConstantEnum.KEY_SPLIT.getValue() + inputKey;
        // 获取Redis分布式锁
        RLock lock = redissonClient.getLock(lockKey);
        // 标记是否获取到锁
        boolean isLocked = false;
        try {
            // 尝试获取锁，并设置超时和等待时间
            isLocked = lock.tryLock();
            // 如果获取到锁且操作对应的标识不存在，则创建标识并返回true，表示操作未执行过
            if (isLocked && getStr(key) == null) {
                setStr(key, inputVal, hour);
                return true;
            } else {
                // 如果未获取到锁或操作对应的标识已存在，记录日志并返回false
                log.info("Failed to acquire lock for idempotent operation: {}", lockKey);
            }
        } catch (Exception e){
            // 如果在等待获取锁的过程中被中断，记录错误日志
            log.error("isLocked is error", e);
        }finally {
            // 如果获取到锁，释放锁
            if (isLocked) {
                lock.unlock();
            }
        }
        // 如果未获取到锁或操作对应的标识已存在，返回false
        return false;
    }


    /**
     * 检查操作是否已被锁定
     *
     * @param operationName 操作名称
     * @param inputKey 请求标识
     * @return 如果操作已被锁定，则返回true；否则返回false
     */
    public boolean isLockedRebug(String operationName, String inputKey, String inputVal, Integer hour) {
        // 构造锁的唯一键值
        String lockKey = IDEMPOTENT_LOCK_KEY + ConstantEnum.KEY_SPLIT.getValue() + operationName + ConstantEnum.KEY_SPLIT.getValue() + inputKey;
        // 构造操作的唯一键值
        String key = IDEMPOTENT_KEY + ConstantEnum.KEY_SPLIT.getValue() + operationName + ConstantEnum.KEY_SPLIT.getValue() + inputKey;
        // 获取Redis分布式锁
        RLock lock = redissonClient.getLock(lockKey);
        // 标记是否获取到锁
        boolean isLocked = false;
        try {
            // 尝试获取锁，并设置超时和等待时间
            isLocked = lock.tryLock();
            log.info("3-1. {} 获得Redis锁后，睡眠60秒---",getCurThreadName());
            Thread.sleep(1000 * 60);

            // 如果获取到锁且操作对应的标识不存在，则创建标识并返回true，表示操作未执行过
            if (isLocked && getStr(key) == null) {
                setStr(key, inputVal+getCurThreadName(), hour);

                log.info("3-2. {} 业务逻辑执行官完成后，返回true 睡眠60秒---",getCurThreadName());
                Thread.sleep(1000 * 60);
                return true;
            } else {
                // 如果未获取到锁或操作对应的标识已存在，记录日志并返回false
                log.info("Failed to acquire lock for idempotent operation: {},{}", lockKey,getCurThreadName());
            }
        } catch (Exception e){
            // 如果在等待获取锁的过程中被中断，记录错误日志
            log.error("isLocked is error", e);
        }finally {
            // 如果获取到锁，释放锁
            if (isLocked) {

                try {
                    log.info("3-3. {} finally 释放锁前 睡眠60秒---",getCurThreadName());
                    Thread.sleep(1000 * 60);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                lock.unlock();
            }
        }
        // 如果未获取到锁或操作对应的标识已存在，返回false
        return false;
    }

    private String getCurThreadName(){
       return "【Thread "+Thread.currentThread().getName() +":"+ Thread.currentThread().getId()+"】";
    }
}
