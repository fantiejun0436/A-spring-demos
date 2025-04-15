package tech.pdai.springboot.mysql57.mybatis.xml.service.impl;

import org.springframework.stereotype.Service;
import tech.pdai.springboot.mysql57.mybatis.xml.dao.IRoleDao;
import tech.pdai.springboot.mysql57.mybatis.xml.entity.Role;
import tech.pdai.springboot.mysql57.mybatis.xml.entity.query.RoleQueryBean;
import tech.pdai.springboot.mysql57.mybatis.xml.service.IRoleService;

import java.util.Collections;
import java.util.List;
@Service
public class RoleDoServiceImpl implements IRoleService {

    private final IRoleDao iroleDao;

    public RoleDoServiceImpl(IRoleDao iroleDao) {
        this.iroleDao = iroleDao;
    }

    @Override
    public List<Role> findList(RoleQueryBean roleQueryBean) {
        return Collections.emptyList();
    }
}
