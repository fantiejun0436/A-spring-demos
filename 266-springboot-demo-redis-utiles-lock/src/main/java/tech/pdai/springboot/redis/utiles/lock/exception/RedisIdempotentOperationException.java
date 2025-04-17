package tech.pdai.springboot.redis.utiles.lock.exception;

/**
 * redis幂等性异常
 */
public class RedisIdempotentOperationException extends  RuntimeException {
    public RedisIdempotentOperationException(String message) { super(message); }
    public RedisIdempotentOperationException(String message, Throwable cause) { super(message, cause); }
}
