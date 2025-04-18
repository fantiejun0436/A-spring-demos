package tech.pdai.springboot.redis.utiles.lock.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.pdai.springboot.redis.utiles.lock.common.utiles.ConstantEnum;
import tech.pdai.springboot.redis.utiles.lock.common.utiles.RedisUtils;
import tech.pdai.springboot.redis.utiles.lock.entity.response.ResponseResult;

import javax.annotation.Resource;

@RestController
@RequestMapping("/setKey")
public class SetKeyController {

    private static final String EXCEPTION_KEY = "exception_key";
    private static final String RUN_INST_INFO_KEY = "run_inst_info_key";

    @Resource
    RedisUtils  redisUtils;

    /**
     *  测试异常. 传入一个 workflowId，如果redis中存在，则返回失败，否则返回成功
     * @param workflowId
     * @return
     */
    @PostMapping("exeptionStepSchedule")
    public ResponseResult<Boolean> exeptionStepSchedule(Integer workflowId){
       Integer execFailed;
       Boolean exist;
        String key =redisUtils.getKey(ConstantEnum.KEY_SPLIT.getValue(), EXCEPTION_KEY, RUN_INST_INFO_KEY,String.valueOf(workflowId));
        if (Boolean.TRUE.equals(redisUtils.isExists(key))){
            execFailed =Integer.valueOf(redisUtils.getStr(key));
            exist=true;
        }else {
            execFailed =workflowId;
            redisUtils.setStr(key, String.valueOf(execFailed), 1);
            exist=false;
        }
        return ResponseResult.success(exist);
    }
}
