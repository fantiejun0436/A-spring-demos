package tech.pdai.springboot.redis.utiles.lock.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.pdai.springboot.redis.utiles.lock.entity.response.ResponseResult;
import tech.pdai.springboot.redis.utiles.lock.service.IRedisLockService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redisLockFilePath")
public class RedisLockFilePathController {

    @Resource
    private  IRedisLockService redisLockService;
    private static final String PUB_EXECUTE = "pubExecute";


    /**
     * 第一操作：获取锁,获得锁之后，立即sleeping,让其他并发操作幂等
     * @param opt
     * @param redisKey
     * @param redisVal
     * @param hour
     * @return
     */
    @PostMapping("getLockAndSleeping")
    public ResponseResult<Boolean> getLockAndSleeping(String opt,String redisKey,String redisVal,Integer hour){

        return ResponseResult.success(redisLockService.getFilePathRedisValSleeping(opt,redisKey,redisVal,hour));
    }

    /**
     * 第二操作：获取锁失败，所有操作都被幂等
     * @param opt
     * @param redisKey
     * @param redisVal
     * @param hour
     * @return
     */
    @PostMapping("otherReuqestGetLockQueueing")
    public ResponseResult<Boolean> otherReuqestGetLockQueueing(String opt,String redisKey,String redisVal,Integer hour){

        return ResponseResult.success(redisLockService.getFilePathRedisVal(opt,redisKey,redisVal,hour));
    }

}
