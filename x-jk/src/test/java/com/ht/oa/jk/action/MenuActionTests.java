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
                MockMvcRequestBuilders.post("/menu/add")
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
                MockMvcRequestBuilders.post("/menu/modify")
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
                MockMvcRequestBuilders.post("/menu/findUserMenu")
                        .header("Token", TokenUtils.Token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(params)))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        responseContent = new String(responseContent.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(responseContent);
    }

    /**
     * 查询所有菜单通过角色
     *
     * @throws Exception
     */
    @Test
    public void findAllMenuByRoleId() throws Exception {
        JSONObject params = new JSONObject();

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/menu/findAllMenuByRoleId")
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
                MockMvcRequestBuilders.post("/menu/findAllMenu")
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
                MockMvcRequestBuilders.post("/menu/findMenuByRole")
                        .header("Token", TokenUtils.Token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(params)))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        responseContent = new String(responseContent.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(responseContent);
    }

    /**
     * 获取当前登录用户的菜单
     *
     * @throws Exception
     */
    @Test
    public void findMenu() throws Exception {
        JSONObject params = new JSONObject();

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/menu/findMenu")
                        .header("Token", TokenUtils.Token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(params)))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        responseContent = new String(responseContent.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(responseContent);
    }

}
