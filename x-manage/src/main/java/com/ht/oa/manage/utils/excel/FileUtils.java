/*
 * Created on Mar 20, 2011 10:24:34 AM
 *
 * By SinoBest
 * Copyright hnisi.com.cn, 2005-2006, All rights reserved.
 */

package com.ht.oa.manage.utils.excel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 向客户端下载文件,弹出下载框.
 *
 * @throws IOException
 */

public class FileUtils {

    /**
     * 下载文件
     *
     * @param response
     * @param request
     * @param file
     * @param isDel
     * @throws IOException
     */
    public static void download(HttpServletResponse response, HttpServletRequest request, File file,
                                boolean isDel) throws IOException {

        OutputStream out = null;
        InputStream in = null;
        // 获得文件名
        String filename = URLEncoder.encode(file.getName(), "UTF-8");
        // 定义输出类型(下载)
        response.setContentType("application/force-download");
        response.setHeader("Location", filename);
        // 定义输出文件头
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        out = response.getOutputStream();
        in = new FileInputStream(file.getPath());

        byte[] buffer = new byte[1024];
        int i = -1;
        while ((i = in.read(buffer)) != -1) {
            out.write(buffer, 0, i);
        }
        in.close();
        out.close();
        if (isDel) {
            //删除文件,删除前关闭所有的Stream.
            file.delete();
        }
    }

    /***
     * 判断目录是否存在
     */
    public static void isExistDirIfCan(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

}
