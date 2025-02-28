package com.qxy.learning.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

/**
 * @Description: 类信息描述
 * @Author: qinxiaoyun001@lianjia.com
 * @Date: 2020/4/30 10:15 AM
 * @Version: 1.0
 */
public class NioTimeServer implements Runnable{

    private volatile boolean stop;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public NioTimeServer(int port) throws IOException {
        //打开 server channel
        this.serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        //设置未非阻塞
        serverSocketChannel.configureBlocking(false);

        //打开 selector
        this.selector = Selector.open();
        //将 channel 注册到 selector 上，监听 接收事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void stop(){
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop){
            try {
                /**
                 * 阻塞等待 事件到达
                 * 如果没有事件到达，此方法会阻塞
                 */
                selector.select(1000);

                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
