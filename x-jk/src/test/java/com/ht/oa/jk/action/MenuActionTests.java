package com.ht.oa.jk.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ht.oa.jk.TokenUtils;
import com.ht.oa.jk.utils.code.ApiCode;
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
public class MenuActionTests {

    @Autowired
    private MockMvc mockMvc;

    /**
     * 添加菜单
     *
     * @throws Exception
     */
    @Test
    public void add() throws Exception {
        JSONObject params = new JSONObject();
        params.put("parentId", 0L);
        params.put("name", "测试菜单");
        params.put("path", "#TestMenu");
        params.put("desc", "测试菜单");
        params.put("icon", "1.jpg");

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post(ApiCode.S003001)
                        .header("Token", TokenUtils.Token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(params)))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        responseContent = new String(responseContent.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(responseContent);
    }

    /**
     * 修改菜单
     *
     * @throws Exception
     */
    @Test
    public void modify() throws Exception {
        JSONObject params = new JSONObject();
        params.put("id", 710617843766530048L);
        params.put("parentId", 0L);
        params.put("name", "测试菜单");
        params.put("path", "#TestMenu");
        params.put("desc", "测试菜单2");
        params.put("icon", "1.jpg");

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post(ApiCode.S003002)
                        .header("Token", TokenUtils.Token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(params)))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        responseContent = new String(responseContent.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(responseContent);
    }

    /**
     * 查询指定用户的菜单
     *
     * @throws Exception
     */
    @Test
    public void findUserMenu() throws Exception {
        JSONObject params = new JSONObject();
        params.put("page", 1);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post(ApiCode.S003003)
                        .header("Token", TokenUtils.Token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(params)))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        responseContent = new String(responseContent.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(responseContent);
    }

    /**
     * 查询所有菜单
     *
     * @throws Exception
     */
    @Test
    public void findAllMenu() throws Exception {
        JSONObject params = new JSONObject();

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post(ApiCode.S003004)
                        .header("Token", TokenUtils.Token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(params)))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        responseContent = new String(responseContent.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(responseContent);
    }

    /**
     * 查询菜单通过角色
     *
     * @throws Exception
     */
    @Test
    public void findMenuByRole() throws Exception {
        JSONObject params = new JSONObject();
        params.put("roleId", 706456971917791232L);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post(ApiCode.S003005)
                        .header("Token", TokenUtils.Token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(params)))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        responseContent = new String(responseContent.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(responseContent);
    }

}
