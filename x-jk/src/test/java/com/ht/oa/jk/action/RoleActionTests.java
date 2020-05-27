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
public class RoleActionTests {

    @Autowired
    private MockMvc mockMvc;

    /**
     * 添加角色
     *
     * @throws Exception
     */
    @Test
    public void add() throws Exception {
        JSONObject params = new JSONObject();
        params.put("roleName", "质量管理员");

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/role/add")
                        .header("Token", TokenUtils.Token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(params)))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        responseContent = new String(responseContent.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(responseContent);
    }


    /**
     * 查询角色
     *
     * @throws Exception
     */
    @Test
    public void list() throws Exception {
        JSONObject params = new JSONObject();
        params.put("page", 1);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/role/list")
                        .header("Token", TokenUtils.Token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(params)))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        responseContent = new String(responseContent.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(responseContent);
    }

    /**
     * 修改角色
     *
     * @throws Exception
     */
    @Test
    public void modify() throws Exception {
        JSONObject params = new JSONObject();
        params.put("roleId", 706456971917791232L);
        params.put("roleName", "高2");

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/role/modify")
                        .header("Token", TokenUtils.Token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(params)))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        responseContent = new String(responseContent.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(responseContent);
    }

    /**
     * 删除角色
     *
     * @throws Exception
     */
    @Test
    public void del() throws Exception {
        JSONObject params = new JSONObject();
        params.put("roleId", 706456971917791232L);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/role/del")
                        .header("Token", TokenUtils.Token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(params)))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        responseContent = new String(responseContent.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(responseContent);
    }

}
