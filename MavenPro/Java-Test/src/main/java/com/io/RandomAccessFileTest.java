package com.io;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

class User {
    int id;
    String name;
    int age;

    public User() {
    }

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class RandomAccessFileTest {
    public static void main(String[] args) throws Exception {
        String value = "中国";
        System.out.println((int)value.charAt(0));
        System.out.println((int)value.charAt(1));
//        System.out.println(value.length());
//        testWriteReadBuffer();
        int v1=20013;
        int v2=22269;
        char c = (char)v1;
        System.out.println(c);
    }
    public static void testWriteReadBuffer() throws Exception {
        final int BUFFERED_SIZE = 10 * 512;
        byte[] buffer = new byte[BUFFERED_SIZE];
        String[] dataFormat = {"int", "String", "int"};

        List<User> list = new ArrayList<>();
        list.add(new User(1, "Sam", 18));
        list.add(new User(2, "Jack", 20));
        list.add(new User(3, "Lucy", 19));
        list.add(new User(4, "Lily", 17));
        int rowSize = 4 + 8 + 4;

        int start = 0;
        for(User u: list){
            start = saveUser(u, rowSize, buffer, start);
        }
        System.out.println("Final position is:"+start);

        System.out.println(getUser(buffer, 32));
    }

    private static int saveUser(User u, int rowSize, byte[] buffer, int start) throws Exception {
        byte[] tmp = new byte[rowSize];
        writeInt(tmp, 0, u.id);
        writeString(tmp, 4, u.name);
        writeInt(tmp,  12, u.age);

        System.arraycopy(tmp, 0, buffer, start, rowSize);
        start += rowSize;
        return start;
    }

    private static User getUser(byte[] buffer, int start) throws Exception {
        User u = new User();
        u.id = readInt(buffer, start);
        u.name = readString(buffer, start + 4);
        u.age = readInt(buffer, start + 12);
        return u;
    }

    //4 bytes for int
    private static void writeInt(byte[] buffer, int start, int value) {
        buffer[start + 0] = (byte) ((value >>> 24) & 0xff);
        buffer[start + 1] = (byte) ((value >>> 16) & 0xff);
        buffer[start + 2] = (byte) ((value >>> 8) & 0xff);
        buffer[start + 3] = (byte) ((value) & 0xff);
    }

    private static int readInt(byte[] buffer, int start) {
        int ch1 = (buffer[start + 0] & 0xff) << 24;
        int ch2 = (buffer[start + 1] & 0xff) << 16;
        int ch3 = (buffer[start + 2] & 0xff) << 8;
        int ch4 = (buffer[start + 3] & 0xff);
        return ch1 + ch2 + ch3 + ch4;
    }

    //8 bytes for int
    private static void writeLong(byte[] buffer, int start, long value) {
        buffer[start + 0] = (byte) ((value >>> 56) & 0xff);
        buffer[start + 1] = (byte) ((value >>> 48) & 0xff);
        buffer[start + 2] = (byte) ((value >>> 40) & 0xff);
        buffer[start + 3] = (byte) ((value >>> 32) & 0xff);
        buffer[start + 4] = (byte) ((value >>> 24) & 0xff);
        buffer[start + 5] = (byte) ((value >>> 16) & 0xff);
        buffer[start + 6] = (byte) ((value >>> 8) & 0xff);
        buffer[start + 7] = (byte) ((value) & 0xff);
    }

    private static long readLong(byte[] buffer, int start) {
        long ch1 = (long) (buffer[start + 0] & 0xff) << 56;
        long ch2 = (long) (buffer[start + 1] & 0xff) << 48;
        long ch3 = (long) (buffer[start + 2] & 0xff) << 40;
        long ch4 = (long) (buffer[start + 3] & 0xff) << 32;
        long ch5 = (long) (buffer[start + 4] & 0xff) << 24;
        long ch6 = (long) (buffer[start + 5] & 0xff) << 16;
        long ch7 = (long) (buffer[start + 6] & 0xff) << 8;
        long ch8 = (long) (buffer[start + 7] & 0xff) << 0;
        return ch1 + ch2 + ch3 + ch4 + ch5 + ch6 + ch7 + ch8;
    }

    //string limited to length 7 bytes, last byte use to save string length
    private static void writeString(byte[] buffer, int start, String value) throws Exception {
        if (value.length() > 7)
            throw new Exception("length can't bigger than 7 bytes");
        int len = value.length();
        long v = 0L;
        for (int i = 0; i < len; i++) {
            char c = value.charAt(i);
            if ((int) c < 127) {//only handle 1 byte
                v += (c & 0xff);
                v = v << 8; //in order to save last byte with string length
            } else {
                throw new Exception("Only handle ascii code char");
            }
        }
        v += len;   //save last byte with length
        writeLong(buffer, start, v);
    }

    private static String readString(byte[] buffer, int start) {
        long v = readLong(buffer, start);
//        System.out.println("Read long value:"+v);
        int len = (int)(v & 0xff);
        v = v >>> 8;
//        System.out.println("String length is:"+len);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<len; i++){
            char c1 = (char)(v & 0xff);
            sb.append(c1);
            v = v >>> 8;
        }
        return sb.reverse().toString();
    }


    public static void test(String[] args) throws Exception {
//        int i = 1;
//        int v = i << 2;
//        System.out.println(Integer.toBinaryString(v));

        byte[] bytes = new byte[1];
        RandomAccessFile file = new RandomAccessFile("D:\\code\\github\\service\\MavenPro\\Java-Test\\src\\main\\java\\com\\io\\file.txt", "rw");
        long p = file.getFilePointer();
        int v = 33333;
        byte[] b = new byte[4];


        //write to b
        b[3] = (byte) ((v >>> 24) & 0xFF);
        b[2] = (byte) ((v >>> 16) & 0xFF);
        b[1] = (byte) ((v >>> 8) & 0xFF);
        b[0] = (byte) ((v >>> 0) & 0xFF);

        System.out.println(b);
        //read from b
        int nextV = ((b[3] & 0xFF) << 24) + ((b[2] & 0xFF) << 16) + ((b[1] & 0xFF) << 8) + (int) (b[0] & 0xFF);
        System.out.println(nextV);

//        file.seek(100000);
//        file.write("k".getBytes());
//        file.write("a".getBytes());
//        file.write("b".getBytes());
//        file.write("c".getBytes());
//        file.seek(2);
//        file.write("d".getBytes());
//        file.seek(3);
//        file.read(bytes);
//        System.out.println(file.length());
//        System.out.println(new String(bytes));
        file.close();
    }

}
