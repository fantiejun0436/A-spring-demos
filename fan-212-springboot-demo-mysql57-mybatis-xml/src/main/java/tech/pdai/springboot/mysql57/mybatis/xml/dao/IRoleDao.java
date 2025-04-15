package tech.pdai.springboot.mysql57.mybatis.xml.dao;

import org.apache.ibatis.annotations.Mapper;
import tech.pdai.springboot.mysql57.mybatis.xml.entity.Role;
import tech.pdai.springboot.mysql57.mybatis.xml.entity.query.RoleQueryBean;

import java.util.List;

@Mapper
public interface IRoleDao {
    List<Role> findList(RoleQueryBean roleQueryBean);
}
