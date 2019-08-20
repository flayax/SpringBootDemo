package com.flaya.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GirlControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    public void girlList() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/spring_boot/girl/girls"))
//                .andExpect(MockMvcResultMatchers.status().isOk()) // 返回码是否为200
//                .andExpect(MockMvcResultMatchers.content().string("abc")); // 返回值是否为“abc”
//    }

    @Test
    public void girlAdd() {

    }
}