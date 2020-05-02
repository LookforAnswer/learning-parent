package com.qxy.learning.java.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * @Description: 类信息描述
 * @Author: qinxiaoyun001@lianjia.com
 * @Date: 2020/4/30 10:17 AM
 * @Version: 1.0
 */
public class TimeServerHandler implements Runnable {

    private Socket request;

    public TimeServerHandler(Socket request) {
        this.request = request;
    }

    @Override
    public void run() {

        BufferedReader in = null;
        PrintWriter out = null;

        try {

            /**
             * 接收请求
             */
            in = new BufferedReader(new InputStreamReader(request.getInputStream()));
            /**
             * readLine方法，如果不结束，会阻塞挂起
             */
            String body = in.readLine();
            System.out.println("the request body is :" + body);

            /**
             * 构造 响应
             */
            out = new PrintWriter(request.getOutputStream());
            String currentTime = "the now time is :" + new Date(System.currentTimeMillis()).toString();
            //将 响应结果 写入 outputStream 流中
            out.println(currentTime);
            // 将 outputStream 流中的数据【用户空间】，同步到【内核空间】
            out.flush();
            // 关闭输出流，客户端才能接受到请求
//            request.shutdownOutput();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
            }
            if (request != null) {
                try {
                    request.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
