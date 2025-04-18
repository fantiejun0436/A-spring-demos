package tech.pdai.springboot.mysql57.mybatis.xml.service.impl;

import org.springframework.stereotype.Service;
import tech.pdai.springboot.mysql57.mybatis.xml.dao.IUserDao;
import tech.pdai.springboot.mysql57.mybatis.xml.entity.User;
import tech.pdai.springboot.mysql57.mybatis.xml.entity.query.UserQueryBean;
import tech.pdai.springboot.mysql57.mybatis.xml.service.IUserService;

import java.nio.file.OpenOption;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserDoServiceImpl implements IUserService {

    private final IUserDao userDao;

    public UserDoServiceImpl(final IUserDao userDao2){
        this.userDao =userDao2;
    }

    @Override
    public List<User> findList(UserQueryBean userQueryBean) {
        return Optional.ofNullable(userDao.findList(userQueryBean)).orElse(Collections.emptyList());
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public int deleteById(Long id) {
        return userDao.deleteById(id);
    }

    @Override
    public int deleteByIds(Long[] ids) {
        return userDao.deleteByIds(ids);
    }

    @Override
    public int save(User user) {
        return userDao.save(user);
    }

    @Override
    public int update(User user) {
        return userDao.update(user);
    }

    @Override
    public int updatePassword(User user) {
        return userDao.updatePassword(user);
    }
}
