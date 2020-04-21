package com.ht.oa.jk.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ht.oa.jk.config.ConfigParam;
import com.ht.oa.jk.model.req.HtDailyReq;
import com.ht.oa.jk.pojo.HtDailyData;
import com.ht.oa.jk.service.DailyService;
import com.ht.oa.jk.utils.auth.AuthTools;
import com.ht.oa.jk.utils.cache.CacheMember;
import com.ht.oa.jk.utils.cache.MemberCacheUtils;
import com.ht.oa.jk.utils.common.BaseUtils;
import com.ht.oa.jk.utils.common.DateUtils;
import com.ht.oa.jk.utils.common.ResultUtils;
import com.ht.oa.jk.utils.common.StringUtils;
import com.ht.oa.jk.utils.excel.FileUtils;
import com.ht.oa.jk.utils.log.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 日报
 */
@Controller
@RequestMapping("/daily")
public class DailyAction {

    @Autowired
    private DailyService dailyService;

    /***
     * 查询日报列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(HttpServletRequest request, HttpServletResponse response) {
        ServletInputStream in = null;
        try {
            in = request.getInputStream();
            StringBuilder requestMsg = new StringBuilder();
            byte[] b = new byte[4096];
            int l;
            while ((l = in.read(b)) != -1) {
                requestMsg.append(new String(b, 0, l, "UTF-8"));
            }
            if (StringUtils.isBlank(requestMsg.toString())) {
                return ResultUtils.paramNoPass("参数必传");
            }
            LogUtils.error(requestMsg.toString());
            JSONObject reqJson = JSON.parseObject(requestMsg.toString());
            String accessToken = AuthTools.getToken(request);
            if (StringUtils.isBlank(accessToken)) {
                return ResultUtils.paramNoPass("accessToken不能为空");
            }
            CacheMember cacheMember = MemberCacheUtils.getCacheMember(accessToken);
            if (cacheMember == null) {
                return ResultUtils.login();
            }
            Integer page = reqJson.getInteger("page");
            if (page == null) {
                page = 1;
            }
            Integer dailyDateNum = reqJson.getInteger("dailyDate");
            if (dailyDateNum == null) {
                dailyDateNum = DateUtils.getYMdToNum();
            }
            HtDailyReq htDailyReq = new HtDailyReq();
            htDailyReq.setPage(page);
            htDailyReq.setDailyDateNum(dailyDateNum);
            List<Map<String, Object>> list = dailyService.list(htDailyReq);
            return ResultUtils.list(list);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("DailyAction%list", e);
            return ResultUtils.exception();
        }
    }

    /**
     * 导出的日报
     */
    @RequestMapping("/exportDaily")
    public void exportDaily(HttpServletRequest request, HttpServletResponse response) {
        try {
            String filePath = ConfigParam.fileSavePrefixPath + BaseUtils.getUuid();
            FileUtils.isExistDirIfCan(filePath);
            // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
            // 如果这里想使用03 则 传入excelType参数即可
            String date = DateUtils.getYMd();
//            String date = "2020-04-15";
            filePath += "/" + date + "-日报.xlsx";
            List<HtDailyData> list = dailyService.queryListByDate(date);
            if (list != null && list.size() > 0) {
                for (HtDailyData htDailyData : list) {
                    String isComplete = htDailyData.getIsComplete();
                    if ("1".equals(isComplete)) {
                        htDailyData.setIsComplete("是");
                    } else {
                        htDailyData.setIsComplete("否");
                    }
                }
            }
            EasyExcel.write(filePath, HtDailyData.class).sheet("日报").doWrite(list);
            FileUtils.download(response, request, new File(filePath), true);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("DailyAction%exportDaily", e);
        }
    }

}
