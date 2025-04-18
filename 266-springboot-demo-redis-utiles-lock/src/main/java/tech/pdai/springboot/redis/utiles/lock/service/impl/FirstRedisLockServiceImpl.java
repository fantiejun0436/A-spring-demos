package tech.pdai.springboot.redis.utiles.lock.service.impl;

import org.springframework.stereotype.Service;
import tech.pdai.springboot.redis.utiles.lock.common.utiles.RedisUtils;
import tech.pdai.springboot.redis.utiles.lock.service.IRedisLockService;

import javax.annotation.Resource;

@Service
public class FirstRedisLockServiceImpl implements IRedisLockService {

    @Resource
    private RedisUtils redisUtils;


    @Override
    public boolean getFilePathRedisVal(String opt,String redisKey,String redisVal,int hour){

        boolean isLocked =redisUtils.isLocked(opt,redisKey,redisVal,2);
        return isLocked;
    }

    @Override
    public boolean getFilePathRedisValSleeping(String opt,String redisKey,String redisVal,int hour){

        boolean isLocked =redisUtils.isLockedRebug(opt,redisKey,redisVal,2);
        return isLocked;
    }
}
