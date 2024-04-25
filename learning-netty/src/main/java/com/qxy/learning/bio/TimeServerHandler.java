package com.qxy.learning.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable {

    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            //获取 socket 的 inputStream
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            //获取 socket 的 outputStream
            out = new PrintWriter(this.socket.getOutputStream(),true);

            String currentTime = null;

            String body = null;
            while (true){
                /**
                 * 读取 socket 数据
                 */
                body = in.readLine();//从 inputStream 读取 客户端请求过来的 信息
                if (body == null){//数据读取完成
                    break;
                }

                /**
                 * 构造 socket 数据响应
                 */
                System.out.println("The time server receive order :" + body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)
                        ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";

                //将结果写回socket
                out.println(currentTime);
            }
        } catch (Exception e){
            if (in != null){
                try {
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            if (out != null){
                out.close();
                out = null;
            }

            if (this.socket != null){
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                this.socket = null;
            }
        }
    }
}
