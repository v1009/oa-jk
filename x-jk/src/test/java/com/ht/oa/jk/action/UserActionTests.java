package com.ht.oa.jk.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ht.oa.jk.TokenUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserActionTests {

    @Autowired
    private MockMvc mockMvc;

    /**
     * 添加用户
     *
     * @throws Exception
     */
    @Test
    public void add() throws Exception {
        JSONObject params = new JSONObject();
        params.put("mobile", "13157184276");
        params.put("userName", "高");
        params.put("pwd", "123456");
        params.put("email", "934127319@qq.com");

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/user/add")
                        .header("Token", TokenUtils.Token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(params)))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        responseContent = new String(responseContent.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(responseContent);
    }


    /**
     * 查询用户
     *
     * @throws Exception
     */
    @Test
    public void list() throws Exception {
        JSONObject params = new JSONObject();
        params.put("page", 1);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/user/list")
                        .header("Token", TokenUtils.Token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(params)))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        responseContent = new String(responseContent.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(responseContent);
    }

    /**
     * 修改用户
     *
     * @throws Exception
     */
    @Test
    public void modify() throws Exception {
        JSONObject params = new JSONObject();
        params.put("userId", 702219243709665280L);
        params.put("userName", "高2");
        params.put("email", "934127319@qq.com");

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/user/modify")
                        .header("Token", TokenUtils.Token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(params)))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        responseContent = new String(responseContent.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(responseContent);
    }

}
