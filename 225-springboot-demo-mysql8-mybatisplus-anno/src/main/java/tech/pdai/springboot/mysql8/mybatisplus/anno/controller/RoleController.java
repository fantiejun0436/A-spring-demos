package tech.pdai.springboot.mysql8.mybatisplus.anno.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.pdai.springboot.mysql8.mybatisplus.anno.entity.Role;
import tech.pdai.springboot.mysql8.mybatisplus.anno.entity.query.RoleQueryBean;
import tech.pdai.springboot.mysql8.mybatisplus.anno.entity.response.ResponseResult;
import tech.pdai.springboot.mysql8.mybatisplus.anno.service.IRoleService;

import java.util.List;

/**
 * @author pdai
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * @return user list
     */
    @ApiOperation("Query Role List")
    @GetMapping("list")
    public ResponseResult<List<Role>> list(RoleQueryBean roleQueryBean) {
        return ResponseResult.success(roleService.findList(roleQueryBean));
    }
}
