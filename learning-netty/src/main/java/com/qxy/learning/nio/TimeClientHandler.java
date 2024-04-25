package com.qxy.learning.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TimeClientHandler implements Runnable {

    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private boolean stop;

    public TimeClientHandler(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            this.selector = Selector.open();
            this.socketChannel = SocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void run() {
        try {
            doConnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeySet.iterator();
                SelectionKey key = null;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();
                    handlerInput(key);
                    if (key.channel() != null){
                        key.channel().close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        if (selector != null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理输入
     * @param key
     */
    private void handlerInput(SelectionKey key) throws IOException {
        //判断是否连接成功
        if (key.isValid()){
            SocketChannel sc = (SocketChannel) key.channel();
            //判断是否连接成功
            if (key.isConnectable()){
                if (socketChannel.finishConnect()){
                    sc.register(selector, SelectionKey.OP_READ);
                    //如果连接成功，则发送请求
                    doWrite(sc);
                } else {
                    System.exit(1);
                }
            }

            //处理响应结果
            if (key.isReadable()){

            }
        }
    }

    /**
     * 创建连接
     * @throws IOException
     */
    private void doConnect() throws IOException {
        if (socketChannel.connect(new InetSocketAddress(host, port))){
            socketChannel.register(selector,SelectionKey.OP_READ);
            //发送请求
            doWrite(socketChannel);
        } else{
            socketChannel.register(selector,SelectionKey.OP_CONNECT);
        }
    }

    /**
     * 发送请求
     * @param socketChannel
     * @throws IOException
     */
    private void doWrite(SocketChannel socketChannel) throws IOException {
        byte[] req = "QUERY TIME ORDER".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
        if (!writeBuffer.hasRemaining()){
            System.out.println("send order 2 server succeed");
        }
    }
}
