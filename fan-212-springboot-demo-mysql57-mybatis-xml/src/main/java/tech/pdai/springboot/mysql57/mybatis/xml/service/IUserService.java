package tech.pdai.springboot.mysql57.mybatis.xml.service;

import tech.pdai.springboot.mysql57.mybatis.xml.entity.User;
import tech.pdai.springboot.mysql57.mybatis.xml.entity.query.UserQueryBean;

import java.util.List;

/**
 * user service interface.
 *
 * @author pdai
 */
public interface IUserService {

    List<User> findList(UserQueryBean userQueryBean);

    User findById(Long id);

    int deleteById(Long id);

    int deleteByIds(Long [] ids);

    int update(User user);

    int updatePassword(User user);
}
