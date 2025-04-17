package tech.pdai.springboot.redis.utiles.lock.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.pdai.springboot.redis.utiles.lock.entity.User;
import tech.pdai.springboot.redis.utiles.lock.entity.response.ResponseResult;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private RedisTemplate<String, User> redisTemplate;


    @ApiOperation("Add")
    @PostMapping("add")
    public ResponseResult<User> add(User user){
        redisTemplate.opsForValue().set(String.valueOf(user.getId()), user);
        return ResponseResult.success(redisTemplate.opsForValue().get(String.valueOf(user.getId())));
    }

    @ApiOperation("Find")
    @GetMapping("find/{userId}")
    public ResponseResult<User> findUserById(@PathVariable("userId")String userId ){
        return ResponseResult.success(redisTemplate.opsForValue().get(userId));
    }
}
