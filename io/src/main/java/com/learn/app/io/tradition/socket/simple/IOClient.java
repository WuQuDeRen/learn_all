package com.learn.app.io.tradition.socket.simple;

import java.io.*;
import java.net.Socket;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-26 11:26
 */
public class IOClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 8201);
        new Thread(() -> {
            try {
                write(socket);
                getInputStream(socket);
                return;
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void write(Socket socket) {
        new Thread(() -> {
            int i = 0;
            try {
                while (true) {
                    OutputStream outputStream = socket.getOutputStream();

                    outputStream.write("请求服务器".getBytes());
                    outputStream.flush();
                    // shutdownOutput 这个是去关闭 此次 socket 的客户端方面的输出流，
                    // 为的是让服务端的socket输入流知道本次输出流已经结束了，不要再循环等待看此次是否结束
                    // socket.shutdownOutput();

                    Thread.sleep(3000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

    public static void getBuffered(Socket socket) throws IOException, InterruptedException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while (true) {
            String line = bufferedReader.readLine();
            System.out.println(line);
            Thread.sleep(2000);
        }
    }

    public static void getInputStream(Socket socket) throws IOException, InterruptedException {
        InputStream inputStream = socket.getInputStream();
        while (true) {

            byte[] read = new byte[1024];
            int len ;
            while ((len = inputStream.read(read)) != -1) {
                String str = new String(read, 0, len);
                System.out.println(str);
            }
            Thread.sleep(2000);
        }
    }
}
