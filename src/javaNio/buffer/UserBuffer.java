package javaNio.buffer;

import java.nio.IntBuffer;

public class UserBuffer {
    static IntBuffer intBuffer=null;

    public static void allocatTest(){
        intBuffer=IntBuffer.allocate(20);
        System.out.println("--------after allocate-------");
        System.out.println("position="+intBuffer.position());
        System.out.println("limit="+intBuffer.limit());
        System.out.println("capacity="+intBuffer.capacity());
    }

    public static void main(String[] args) {
        UserBuffer.allocatTest();
    }
}
