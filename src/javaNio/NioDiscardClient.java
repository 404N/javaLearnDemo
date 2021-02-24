package javaNio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioDiscardClient {
    public static void startClient() throws IOException {
        InetSocketAddress address=new InetSocketAddress(InetAddress.getLocalHost(),8088);
        // 获取通道
        SocketChannel socketChannel=SocketChannel.open(address);
        //切换为非阻塞模式
        socketChannel.configureBlocking(false);
        //不断自旋，等待连接完成，或作一些其他事
        while(!socketChannel.finishConnect()){
            System.out.println("waiting....");
        }
        System.out.println("连接成功");
        //分配指定大小的缓冲区
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        byteBuffer.put("hi word".getBytes());
        byteBuffer.flip();
        //发送到服务器
        socketChannel.write(byteBuffer);
        socketChannel.shutdownOutput();
        socketChannel.close();
    }

    public static void main(String[] args) throws IOException {
        startClient();
    }
}
