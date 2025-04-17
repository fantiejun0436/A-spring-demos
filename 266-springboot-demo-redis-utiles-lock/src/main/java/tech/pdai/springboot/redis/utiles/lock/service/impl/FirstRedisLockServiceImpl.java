package tech.pdai.springboot.redis.utiles.lock.service.impl;

import org.springframework.stereotype.Service;
import tech.pdai.springboot.redis.utiles.lock.common.utiles.RedisUtils;

import javax.annotation.Resource;

@Service
public class FirstRedisLockServiceImpl {

    @Resource
    private RedisUtils redisUtils;

    private static final String PUB_EXECUTE = "pubExecute";

    public String getFilePathRedisVal(){
        return "";
    }
}
