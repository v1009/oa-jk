package com.ht.oa.manage.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.ht.oa.manage.config.ConfigParam;
import com.ht.oa.manage.pojo.HtDailyData;
import com.ht.oa.manage.service.DailyService;
import com.ht.oa.manage.model.HtDaily;
import com.ht.oa.manage.model.code.ResultCode;
import com.ht.oa.manage.model.resp.CommonResponseSimple;
import com.ht.oa.manage.utils.common.BaseUtils;
import com.ht.oa.manage.utils.common.DateUtils;
import com.ht.oa.manage.utils.common.ResultUtils;
import com.ht.oa.manage.utils.common.StringUtils;
import com.ht.oa.manage.utils.excel.FileUtils;
import com.ht.oa.manage.utils.log.LogUtils;
import com.ht.oa.manage.utils.seq.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * 日报
 */
@Controller
@RequestMapping("/daily")
public class DailyAction {

    @Autowired
    private DailyService dailyService;

    /**
     * 填写界面
     */
    @RequestMapping("/fill.htm")
    public String fill(ModelMap modelMap) {
        String today = DateUtils.getYMd();
        modelMap.put("today", today);
        return "daily/fill";
    }

    /***
     * 保存
     */
    @RequestMapping("/save")
    @ResponseBody
    public Object save(HttpServletRequest request, HttpServletResponse response, HtDaily htDaily) {
        CommonResponseSimple commonResponse = new CommonResponseSimple();
        try {
            String userName = htDaily.getUserName();
            if (StringUtils.isBlank(userName)) {
                return ResultUtils.checkValid("姓名不能为空");
            }
            String planContent = htDaily.getPlanContent();
            if (StringUtils.isBlank(planContent)) {
                return ResultUtils.checkValid("计划工作内容不能为空");
            }
            Date now = DateUtils.getNowDate();
            htDaily.setDailyDate(now);
            htDaily.setIsComplete(0);
            htDaily.setInsertTime(now);
            htDaily.setLastTime(now);
            htDaily.setStatus(1);
            boolean boo = false;
            if (htDaily.getId() != null && htDaily.getId() > 0) {
                boo = dailyService.modify(htDaily);
            } else {
                htDaily.setId(IdWorker.nextId());
                boo = dailyService.add(htDaily);
            }
            if (boo) {
                commonResponse.setCode(ResultCode.success.code());
                commonResponse.setResMsg("提交成功");
            } else {
                commonResponse.setCode(ResultCode.busiError.code());
                commonResponse.setResMsg("提交失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.setCode(ResultCode.failure.code());
            commonResponse.setResMsg(ResultCode.failure.desc());
            LogUtils.error("DailyAction%save:", e);
        }
        return commonResponse;
    }

    /**
     * 填写今天完成的内容【一般下班前填写】
     */
    @RequestMapping("/complete.htm")
    public String complete(ModelMap modelMap) {
        String today = DateUtils.getYMd();
        modelMap.put("today", today);
        return "daily/complete";
    }

    /***
     * 下班前完成提交
     */
    @RequestMapping("/completeSubmit")
    @ResponseBody
    public Object completeSubmit(HttpServletRequest request, HttpServletResponse response, HtDaily htDaily) {
        CommonResponseSimple commonResponse = new CommonResponseSimple();
        try {
            int isComplete = htDaily.getIsComplete();
            if (isComplete == 0) {
                String userName = htDaily.getUserName();
                if (StringUtils.isBlank(userName)) {
                    return ResultUtils.checkValid("姓名不能为空");
                }
                String completeContent = htDaily.getCompleteContent();
                if (StringUtils.isBlank(completeContent)) {
                    return ResultUtils.checkValid("完成内容不能为空");
                }
                String problem = htDaily.getProblem();
                if (StringUtils.isBlank(problem)) {
                    return ResultUtils.checkValid("遇到的问题不能为空");
                }
            }
            Date now = DateUtils.getNowDate();
            htDaily.setDailyDate(now);
            htDaily.setLastTime(now);
            htDaily.setStatus(1);
            boolean boo = dailyService.completeSubmit(htDaily);
            if (boo) {
                commonResponse.setCode(ResultCode.success.code());
                commonResponse.setResMsg("提交成功");
            } else {
                commonResponse.setCode(ResultCode.busiError.code());
                commonResponse.setResMsg("提交失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.setCode(ResultCode.failure.code());
            commonResponse.setResMsg(ResultCode.failure.desc());
            LogUtils.error("DailyAction%completeSubmit:", e);
        }
        return commonResponse;
    }

    /**
     * 导出当天的日报
     */
    @RequestMapping("/exportTodayDaily")
    public void exportTodayDaily(HttpServletRequest request, HttpServletResponse response) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
