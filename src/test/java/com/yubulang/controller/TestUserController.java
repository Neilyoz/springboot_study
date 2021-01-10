package com.yubulang.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yubulang.StartApplication;
import com.yubulang.service.TestUserService;
import com.yubulang.vo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StartApplication.class})
@AutoConfigureMockMvc
public class TestUserController {
    private final Logger log = LoggerFactory.getLogger(TestUserService.class);

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void before() {
        log.info("单元测试开始");
    }

    @After
    public void after() {
        log.info("单元测试结束");
    }

    @Test
    public void test01() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/user/username/{username}", "admin"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        mvcResult.getResponse().setCharacterEncoding("utf8");
        log.info("code={}", mvcResult.getResponse().getStatus());
        log.info("content={}", mvcResult.getResponse().getContentAsString());
    }


    /**
     * 设置事务，执行完毕之后回滚数据
     */
    @Test
    @Transactional
    @Rollback
    public void test02() throws Exception {
        // 创建用户实体
        User user = new User();
        user.setUsername("Hello");
        user.setPassword("123456");

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/user")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        mvcResult.getResponse().setCharacterEncoding("utf8");
        log.info("code={}", mvcResult.getResponse().getStatus());
        log.info("content={}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    @Transactional
    @Rollback
    public void test03() throws Exception {
        // 创建用户实体
        User user = new User();
        user.setId(3);
        user.setUsername("Hello");
        user.setPassword("123456");

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        mvcResult.getResponse().setCharacterEncoding("utf8");
        log.info("code={}", mvcResult.getResponse().getStatus());
        log.info("content={}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    @Transactional
    @Rollback
    public void test04() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.delete("/user/{id}", 3))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        mvcResult.getResponse().setCharacterEncoding("utf8");
        log.info("code={}", mvcResult.getResponse().getStatus());
        log.info("content={}", mvcResult.getResponse().getContentAsString());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
