package tech.pdai.springboot.redis.utiles.lock.service;

/**
 *  用 Redis锁 实现分布式锁，解决幂等问题。
 */
public interface IRedisLockService {

    /**
     * 首先执行：
     * 以 “redisKey” 进行获得锁，执行业务逻辑。
     * 这个是一个调试方法，调用的RedisUtils方法中，会在获得锁时睡眠60秒，方便调试
     * @param opt
     * @param redisKey
     * @param redisVal
     * @param hour
     * @return
     */
    public boolean getFilePathRedisValSleeping(String opt,String redisKey,String redisVal,int hour);

    /**
     * 其次执行：
     *   以 “redisKey” 进行获得锁，执行业务逻辑，让 “getFilePathRedisValSleeping” 先执行，然后获得锁，本方法获得锁失败，全部被幂等 冗余掉。
     * @param opt
     * @param redisKey
     * @param redisVal
     * @param hour
     * @return
     */
    public boolean getFilePathRedisVal(String opt,String redisKey,String redisVal,int hour);
}
