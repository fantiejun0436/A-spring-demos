package tech.pdai.springboot.redis.utiles.lock.controller;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.pdai.springboot.redis.utiles.lock.entity.dto.RunTaskDto;
import tech.pdai.springboot.redis.utiles.lock.entity.response.ResponseResult;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Redis Hash类型操作
 */
@RestController
@RequestMapping("/mapStore")
public class MapStoreRedisController {

    //Redis 键的名称
    private static final String REDIS_KEY = "taskErrorNum";

    @Resource
    private RedisTemplate<String,Map<Long,RunTaskDto>> redisTemplate;




    /**
     * 测试，向redis中放入一个map, 并设置过期时间
     * @return
     */
    @GetMapping("putMapIntoRedis")
    public ResponseResult<Map<Long,RunTaskDto>> putMapIntoRedis(){
        List<RunTaskDto> runTaskDtoList = getRunTaskDtoList();
        Map<Long,RunTaskDto> map=addRedisKey(REDIS_KEY,runTaskDtoList);
        return  ResponseResult.success(map);
    }

    @GetMapping("isExistRedisKey")
    public ResponseResult<Boolean> isExistRedisKey(String key){
        if (redisTemplate.hasKey(key)){
            Map<Object, Object> redisMap =  redisTemplate.opsForHash().entries(key);
            Optional.ofNullable(redisMap).ifPresent(redisMap1 -> {
                redisMap1.forEach((k,v)->{
                    if (v instanceof RunTaskDto){
                        RunTaskDto runTaskDto = (RunTaskDto) v;
                        System.out.println(runTaskDto.getId());
                    }
                });
            });
        }
        return ResponseResult.success(redisTemplate.hasKey(key));
    }

    private List<RunTaskDto> getRunTaskDtoList(){
        List<RunTaskDto> runTaskDtoList =new ArrayList<>();
        for (int i=0;i<30;i++){
            RunTaskDto runTaskDto = new RunTaskDto();
            runTaskDto.setId(Long.valueOf(i));
            runTaskDto.setBusinessSystemName("业务系统:"+ runTaskDto.getId());
            runTaskDto.setEnvNames("环境:"+ runTaskDto.getId());
            runTaskDtoList.add(runTaskDto);
        }
        return runTaskDtoList;
    }


    private Map<Long ,RunTaskDto>addRedisKey(String key,List<RunTaskDto> runTaskDtoList){
        Map<Long,RunTaskDto> map= runTaskDtoList.stream()
        .filter(runTaskDto -> runTaskDto.getId()!=null)
        .collect(Collectors.toMap(RunTaskDto::getId, runTaskDto -> runTaskDto));

        if (null!=map){
            redisTemplate.opsForHash().putAll(key,map);
            redisTemplate.expire(key,3, TimeUnit.MINUTES);
        }
        return map;
    }

}
