package com.yubulang.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yubulang.dao.UserDao;
import com.yubulang.query.UserQuery;
import com.yubulang.utils.AssertUtil;
import com.yubulang.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    public UserDao userDao;

    public User queryUserByUsername(String username) {
        return userDao.queryUserByUsername(username);
    }

    public User queryUserById(Integer id) {
        return userDao.queryUserById(id);
    }

    public void saveUser(User user) {
        AssertUtil.isTrue(StringUtils.isBlank(user.getUsername()), "用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getPassword()), "密码不能为空");
        AssertUtil.isTrue(null != userDao.queryUserByUsername(user.getUsername()), "该用户已经存在");
        AssertUtil.isTrue(userDao.save(user) < 1, "用户添加失败");
    }

    public void updateUser(User user) {
        AssertUtil.isTrue(StringUtils.isBlank(user.getUsername()), "用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getPassword()), "密码不能为空");
        AssertUtil.isTrue(null == userDao.queryUserById(user.getId()), "该用户不存在");
        User tempUser = userDao.queryUserByUsername(user.getUsername());
        AssertUtil.isTrue(null != tempUser && !tempUser.getId().equals(user.getId()), "用户名已存在");
        AssertUtil.isTrue(userDao.update(user) < 1, "用户更新失败");
    }

    public void delete(Integer id) {
        AssertUtil.isTrue(null == id || null == userDao.queryUserById(id), "该用户不存在");
        AssertUtil.isTrue(userDao.delete(id) < 1, "用户删除失败");
    }

    public PageInfo<User> queryUsersByParams(UserQuery userQuery) {
        PageHelper.startPage(userQuery.getPageNum(), userQuery.getPageSize());
        List<User> users = userDao.selectByParams(userQuery);
        return new PageInfo<>(users);
    }
}
