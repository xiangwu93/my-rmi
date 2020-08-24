package com.stu.socket.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author chenxiangwu
 * @title: ServerSocketChannelTest
 * @projectName my-rmi
 * @description: TODO
 * @date 2020/8/21 16:07
 */
public class ServerSocketChannelTest {
    private int size = 1024;
    private ServerSocketChannel socketChannel;
    private ByteBuffer byteBuffer;
    private Selector selector;
    private final int port = 8989;
    private int remoteClientNum = 0;

    public ServerSocketChannelTest() {
        try {
            initChannel();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void initChannel() throws IOException {
        socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.bind(new InetSocketAddress(port));
        System.out.println("------listen on port: " + port);
        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        byteBuffer = ByteBuffer.allocateDirect(size);
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
    }

    private void listener() throws IOException {
        while (true) {
            int n = selector.select();
            if (n == 0) {
                continue;
            }
            Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = ite.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();
                    registerChannel(selector, channel, SelectionKey.OP_READ);
                    remoteClientNum++;
                    System.out.println("------Online client num: " + remoteClientNum);
                    replyClient(channel);
                }
                if (key.isReadable()) {
                    readDataFromSocket(key);
                }

                ite.remove();
            }
        }
    }

    protected void readDataFromSocket(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int count;
        byteBuffer.clear();
        while ((count = socketChannel.read(byteBuffer)) > 0) {
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                socketChannel.write(byteBuffer);
            }
            byteBuffer.clear();
        }
        if (count < 0) {
            socketChannel.close();
        }
    }

    private void registerChannel(Selector selector, SocketChannel channel, int ops) throws IOException {
        if (channel == null){
            return;
        }
        channel.configureBlocking(false);
        channel.register(selector, ops);
    }

    private void replyClient(SocketChannel channel) throws IOException {
       byteBuffer.clear();
       byteBuffer.put("------hello client!\r\n".getBytes());
       byteBuffer.flip();
       channel.write(byteBuffer);
    }

    public static void main(String[] args) {
        try {
            new ServerSocketChannelTest().listener();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
