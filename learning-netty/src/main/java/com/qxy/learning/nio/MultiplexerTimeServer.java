package com.qxy.learning.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MultiplexerTimeServer implements Runnable{

    private Selector selector;

    private ServerSocketChannel serverChannel;

    private volatile  boolean stop;

    /**
     * 服务器初始化
     * @param port
     */
    public MultiplexerTimeServer(int port) throws IOException {
        serverChannel = ServerSocketChannel.open();//开启server通道
        serverChannel.socket().bind(new InetSocketAddress(port));//通道绑定端口

        selector = Selector.open();//创建多路复用器
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);//将 通道 注册到 selector 上

        System.out.println("The time server is start in port:" + port);
    }

    public void stop(){
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop){
            try {
                /**
                 * selector 轮询 所有注册在上边的 通道
                 * 超时时间：1s
                 * 这个过程是阻塞的
                 */
                selector.select(1000);
                //获取 准备就绪的 Server通道
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();

                Iterator<SelectionKey> it = selectionKeySet.iterator();
                SelectionKey key = null;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();
                    //执行每个 server channel
                    handleInput(key);
                    if (key != null){
                        //取消注册，会被添加到 selector 里边 取消集合 里边
                        key.cancel();

                        if (key.channel() != null){
                            key.channel().close();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            //处于可接受状态，已经获取到客户端发送的数据，已经存在内核空间
            if (key.isAcceptable()){
                //获取到 server channel
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();

                //通过 accept 获取到 channel
                SocketChannel socketChannel = ssc.accept();
                socketChannel.configureBlocking(false);     //设置为非阻塞

                //将获取到的 socketChannel 注册 可读 事件
                socketChannel.register(selector, SelectionKey.OP_READ);
            }

            //处于可读状态，数据已经从 内核空间 拷贝到 用户空间
            if (key.isReadable()){

                //可读状态，那么获取的 channel 应该是 SocketChannel
                SocketChannel sc = (SocketChannel) key.channel();

                //NIO都是通过 Buffer 获取数据
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0){
                    //清空数据
                    readBuffer.flip();

                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("The time server receive order : " + body );

                    /**
                     * 生成响应结果，通过 SocketChannel 返回
                     */
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)
                            ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                    doWrite(sc, currentTime);
                } else if (readBytes < 0) {
                    //取消 注册
                    key.channel();
                    //关闭 通道
                    sc.close();
                } else{
                }
            }

        }
    }

    private void doWrite(SocketChannel sc, String currentTime) {

    }
}
