package com.stu.socket.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;

/**
 * @author chenxiangwu
 * @title: SocketChannelTest
 * @projectName my-rmi
 * @description: TODO
 * @date 2020/8/21 16:44
 */
public class SocketChannelTest {

    private int size =1024;
    private ByteBuffer byteBuffer;
    private SocketChannel socketChannel;

    public void connectServer() throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8989));
        byteBuffer=ByteBuffer.allocate(size);
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        receive();
    }

    private void receive() throws IOException {
        while (true) {
            int count;
            byteBuffer.clear();
            while ((count = socketChannel.read(byteBuffer)) > 0){
                byteBuffer.flip();
                while(byteBuffer.hasRemaining()){
                    System.out.println("------ client output: " + (char) byteBuffer.get());
                }
                send("send to server msg".getBytes());
                byteBuffer.clear();
            }
            if(count > 100){
                break;
            }
        }
    }

    private void send(byte[] data) throws IOException {
       byteBuffer.clear();
       byteBuffer.put(data);
       byteBuffer.flip();
       socketChannel.write(byteBuffer);
    }

    public static void main(String[] args) {
        try {
            new SocketChannelTest().connectServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
