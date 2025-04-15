package tech.pdai.springboot.mysql57.mybatis.xml.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.pdai.springboot.mysql57.mybatis.xml.entity.User;
import tech.pdai.springboot.mysql57.mybatis.xml.entity.query.UserQueryBean;
import tech.pdai.springboot.mysql57.mybatis.xml.entity.response.ResponseResult;
import tech.pdai.springboot.mysql57.mybatis.xml.service.IUserService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author tiejun_fan
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @ApiOperation("Query User One")
    @PostMapping("/add")
    public ResponseResult<User> add(User user){
        if (null ==user.getId()){
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            userService.save(user);
        }else{
            user.setUpdateTime(LocalDateTime.now());
            userService.update(user);
        }
        return ResponseResult.success(userService.findById(user.getId()));
    }

    @ApiOperation("Query User One")
    @GetMapping("findById/{userId}")
    public ResponseResult<User> findById(@PathVariable("userId") Long userId){
        return ResponseResult.success(userService.findById(userId));
    }

    @ApiOperation("Query User List")
    @GetMapping
    public ResponseResult<List<User>> list(UserQueryBean userQueryBean){
        return ResponseResult.success(userService.findList(userQueryBean));
    }
}
