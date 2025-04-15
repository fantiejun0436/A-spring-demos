package tech.pdai.springboot.mysql57.mybatis.xml.service;

import tech.pdai.springboot.mysql57.mybatis.xml.entity.Role;
import tech.pdai.springboot.mysql57.mybatis.xml.entity.query.RoleQueryBean;

import java.util.List;

public interface IRoleService {
    List<Role> findList(RoleQueryBean roleQueryBean);
}
