package javaNio.buffer;

import java.nio.IntBuffer;

public class UserBuffer {
    static IntBuffer intBuffer=null;

    public static void allocateTest(){
        intBuffer=IntBuffer.allocate(20);
        disPlay("allocate");
    }

    public static void putTest(){
        for(int i=0;i<5;i++){
            //写数据到缓冲区
            intBuffer.put(i);
        }
        disPlay("put");
    }

    public static void flipTest(){
        //翻转缓冲区，变成读模式
        intBuffer.flip();
        disPlay("flip");
    }

    public static void getTest(){
        for(int i=0;i<2;i++){
            //写数据到缓冲区
            int j=intBuffer.get();
            System.out.println("j="+j);
        }
        disPlay("get");
        for(int i=0;i<3;i++){
            //写数据到缓冲区
            int j=intBuffer.get();
            System.out.println("j="+j);
        }
        disPlay("get");
    }

    public static void reRead(){
        for(int i=0;i<5;i++){
            if(i==2){
                intBuffer.mark();
            }
            int j=intBuffer.get();
            System.out.println("j="+j);
        }
        disPlay("reRead");
    }

    public static void afterReset(){
        intBuffer.reset();
        disPlay("reset");
        for (int i=2;i<5;i++){
            int j=intBuffer.get();
            System.out.println("j="+j);
        }
    }

    public static void clear(){
        intBuffer.clear();
        disPlay("clear");
    }

    public static void disPlay(String title){
        System.out.println("--------after "+title+"-------");
        System.out.println("position="+intBuffer.position());
        System.out.println("limit="+intBuffer.limit());
        System.out.println("capacity="+intBuffer.capacity());
    }

    public static void main(String[] args) {
        UserBuffer.allocateTest();
        UserBuffer.putTest();
        UserBuffer.flipTest();
        UserBuffer.reRead();
        UserBuffer.afterReset();
        UserBuffer.clear();
    }
}
