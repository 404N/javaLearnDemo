package javaNio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioDiscardServer {
    public static void startServer() throws IOException {
        //获取选择器
        Selector selector = Selector.open();
        //获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置非阻塞
        serverSocketChannel.configureBlocking(false);
        //绑定连接
        serverSocketChannel.bind(new InetSocketAddress(8088));
        System.out.println("服务器启动成功");
        //将通道注册的接收新连接IO事件，注册到选择器上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //轮询感兴趣到IO就绪事件
        while (selector.select()>0){
            //获取选择键集合
            Iterator<SelectionKey> selectionKeyIterator=selector.selectedKeys().iterator();
            while(selectionKeyIterator.hasNext()){
                //获取单个的选择键并处理
                SelectionKey selectionKey=selectionKeyIterator.next();
                //判断是什么事件
                if(selectionKey.isAcceptable()){
                    //若是连接就绪事件，就获取客户端连接
                    SocketChannel socketChannel=serverSocketChannel.accept();
                    //切换为非阻塞模式
                    socketChannel.configureBlocking(false);
                    //将该连接的通道可读事件，组册到选择器上
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if(selectionKey.isReadable()){
                    //若为可读事件,读取数据
                    SocketChannel socketChannel=(SocketChannel) selectionKey.channel();
                    //读取数据然后丢弃
                    ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                    int len=0;
                    while((len=socketChannel.read(byteBuffer))>0){
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(),0,len));
                        byteBuffer.clear();
                    }
                    socketChannel.close();
                }
                selectionKeyIterator.remove();
            }
        }
        serverSocketChannel.close();
    }

    public static void main(String[] args) throws IOException {
        startServer();
    }
}
