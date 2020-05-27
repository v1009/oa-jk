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
public class DailyActionTests {

    @Autowired
    private MockMvc mockMvc;

    /**
     * 查询访问记录
     *
     * @throws Exception
     */
    @Test
    public void list() throws Exception {
        JSONObject params = new JSONObject();
        params.put("page", 1);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/daily/list")
                        .header("Token",TokenUtils.Token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(params)))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        responseContent = new String(responseContent.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println(responseContent);
    }


}
