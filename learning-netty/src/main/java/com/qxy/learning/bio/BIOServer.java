package com.qxy.learning.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description: 阻塞IO
 * @Author: qinxiaoyun001@lianjia.com
 * @Date: 2020/1/6 7:59 PM
 * @Version: 1.0
 */
public class BIOServer {

    public static void main(String[] args) throws IOException {
        int port = 1234;
        //开启 端口为 1234
        ServerSocket serverSocket = new ServerSocket(port);
        //accept 接受请求
        Socket clientSocket = serverSocket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String request, response;
        while ((request = in.readLine()) != null){
            if ("Done".equals(request)){
                break;
            }
        }

        response = processRequest(request);
        out.println(response);
    }

    private static String processRequest(String request) {
        return null;
    }

}
