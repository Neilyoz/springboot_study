package com.yubulang.controller;

import com.github.pagehelper.PageInfo;
import com.yubulang.exceptions.ParamsException;
import com.yubulang.model.ResultInfo;
import com.yubulang.query.UserQuery;
import com.yubulang.service.UserService;
import com.yubulang.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "用户模块管理")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("user/username/{username}")
    @ApiOperation(value = "用户模块-根据用户名查询用户记录")
    @ApiImplicitParam(name = "username", value = "查询参数", required = true, paramType = "path")
    public User queryUserByUsername(@PathVariable String username) {
        return userService.queryUserByUsername(username);
    }

    @GetMapping("user/{id}")
    @ApiOperation(value = "用户模块-根据用户id查询用户记录")
    public User queryUserById(@PathVariable Integer id) {
        return userService.queryUserById(id);
    }

    @GetMapping("user/list")
    @ApiOperation(value = "用户模块-用户的列表查询")
    public PageInfo<User> queryUserByParams(UserQuery userQuery) {
        return userService.queryUsersByParams(userQuery);
    }

    @PutMapping("user")
    @ApiOperation(value = "用户模块-用户的添加")
    public ResultInfo saveUser(@RequestBody User user) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            userService.saveUser(user);
        } catch (ParamsException e) {
            resultInfo.setCode(e.getCode());
            resultInfo.setMessage(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            resultInfo.setCode(300);
            resultInfo.setMessage("异常信息");
            e.printStackTrace();
        }

        return resultInfo;
    }

    @PostMapping("user")
    @ApiOperation(value = "用户模块-用户的更新")
    public ResultInfo updateUser(@RequestBody User user) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            userService.updateUser(user);
        } catch (ParamsException e) {
            resultInfo.setCode(e.getCode());
            resultInfo.setMessage(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            resultInfo.setCode(300);
            resultInfo.setMessage("异常信息");
            e.printStackTrace();
        }

        return resultInfo;
    }

    @DeleteMapping("user/{id}")
    @ApiOperation(value = "用户模块-用户的删除")
    public ResultInfo delete(@PathVariable Integer id) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            userService.delete(id);
        } catch (ParamsException e) {
            resultInfo.setCode(e.getCode());
            resultInfo.setMessage(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            resultInfo.setCode(300);
            resultInfo.setMessage("异常信息");
            e.printStackTrace();
        }

        return resultInfo;
    }
}
