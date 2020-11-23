package com;

import java.io.UnsupportedEncodingException;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println("start");
        String a = "DM － A";
        byte[] bytes = a.getBytes();
        String s = new String(bytes, "ISO-8859-1");
        System.out.println("convert :"+s);
        String s1 = new String("中".getBytes("UTF-8"), "ISO8859-1");
        System.out.println("end");
    }
}
