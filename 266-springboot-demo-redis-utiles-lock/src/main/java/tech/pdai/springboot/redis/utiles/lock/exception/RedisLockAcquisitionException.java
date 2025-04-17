package tech.pdai.springboot.redis.utiles.lock.exception;

/**
 * redis获取锁异常类
 */
public class RedisLockAcquisitionException extends RuntimeException {
    public RedisLockAcquisitionException(String message) { super(message); }
    public RedisLockAcquisitionException(String message, Throwable cause) { super(message, cause); }
}
