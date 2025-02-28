package com.qxy.learning.java.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description: 类信息描述
 * @Author: qinxiaoyun001@lianjia.com
 * @Date: 2020/4/30 10:14 AM
 * @Version: 1.0
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;

        ServerSocket server = null;

        try {
            /**
             * 启动 Server
             */
            server = new ServerSocket(port);

            Socket request = null;

            while (true){
                //循环 接收 请求
                request = server.accept();
                //分配 线程 处理
                new Thread(new TimeServerHandler(request)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
