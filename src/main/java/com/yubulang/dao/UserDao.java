package com.yubulang.dao;

import com.yubulang.query.UserQuery;
import com.yubulang.vo.User;

import java.util.List;

public interface UserDao {
    User queryUserByUsername(String username);

    User queryUserById(Integer id);

    int save(User user);

    int update(User user);

    List<User> selectByParams(UserQuery userQuery);

    int delete(Integer id);
}
