package com.qxy.learning.java.bio;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Description: 类信息描述
 * @Author: qinxiaoyun001@lianjia.com
 * @Date: 2020/4/30 10:29 AM
 * @Version: 1.0
 */
public class TimeClient {

    public static void main(String[] args) {

        int port = 8080;

        Socket client = null;

        PrintWriter request = null;

        BufferedReader response = null;

        try {
            client = new Socket("127.0.0.1", port);

            /**
             * 发送请求
             */
            request = new PrintWriter(client.getOutputStream());

            String str = "";
            for (int i = 0 ; i <= 9000; i ++){
                str += "1";
            }
            // 将 结果 写入 outputStream 流中
            /**
             * 默认 < 8192 ，就不会执行刚刚 BufferWrite flush，
             */
            request.println(str);

            // 强制 将 outputStream 流中的数据（用户空间） 同步到 内核空间
            // 如果不 flush ，那么服务端无法 获取导数据
            request.flush();
            // 关闭流，然后服务端才能接受到 请求，在关闭之前一定要 执行 flush 操作（此操作不是必须的）
//            client.shutdownOutput();

            /**
             * 读取响应
             */
            response = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String responseBody = response.readLine();

            System.out.println("the response:" + responseBody);


        } catch (UnknownHostException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            if (request != null){
                request.close();
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (client != null){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
