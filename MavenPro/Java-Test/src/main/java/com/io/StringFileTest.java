package com.io;

import java.io.*;

public class StringFileTest {
    public static void main(String[] args) throws Exception {
        String content = "ttttttttttttttttttttttttttttt11111111";
        ByteArrayInputStream in = new ByteArrayInputStream(content.getBytes());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write("ssssssss".getBytes());
        System.out.println(new String(out.toByteArray()));
        out.writeTo(new FileOutputStream("c:\\temp\\test.txt"));
    }

    private static void writeStringToFile(String content) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(content.getBytes());
        out.writeTo(new FileOutputStream("c:\\temp\\test.txt"));
    }
}
