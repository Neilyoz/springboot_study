package com.yubulang.service;

import com.yubulang.StartApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StartApplication.class})
public class TestUserService {

    private final Logger log = LoggerFactory.getLogger(TestUserService.class);

    @Resource
    private UserService userService;

    @Before
    public void before() {
        log.info("单元测试开始");
    }

    @After
    public void after() {
        log.info("单元测试结束");
    }
}
