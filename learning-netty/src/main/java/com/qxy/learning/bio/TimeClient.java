package com.qxy.learning.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {

    public static void main(String[] args) throws IOException {
        int port = 8080;

        if(args != null && args.length > 0){
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {

            }
        }

        BufferedReader in = null;
        PrintWriter out = null;
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1",port);

            /**
             * 获取 socket outputStream，往里边写，发送请求
             */
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println("QUERY TIME ORDER");

            /**
             * 获取 socket inputStream，读取响应
             */
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = in.readLine();

            System.out.println("Now is : "+response);
        } finally {
            if (in != null){
                in.close();
            }
            if (out != null){
                out.close();
            }
            if (socket != null){
                socket.close();
            }
        }

    }
}
