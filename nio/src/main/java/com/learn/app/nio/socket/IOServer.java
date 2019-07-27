package com.learn.app.nio.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-26 10:57
 */
public class IOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8201);
        while (true) {
            try {
                // 1、阻塞方法获取新的连接
                Socket socket = serverSocket.accept();
                readWrite(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readWrite(Socket socket) {
        new Thread(() -> {
            try {
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                while (true) {
                    byte[] read = new byte[1024];
                    int len;
                    while ((len = inputStream.read(read)) != -1) {
                        // 写
                        outputStream.write("我收到消息了".getBytes());
                        outputStream.flush();
                        // 读
                        System.out.println(new String(read, 0, len));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }


    public static void print(Socket socket) {
        new Thread(() -> {
            try {
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                while (true) {
                    printWriter.println("我收到消息了");
                    printWriter.flush();
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void printStream(Socket socket) {
        new Thread(() -> {
            try {
                OutputStream outputStream = socket.getOutputStream();
                while (true) {
                    outputStream.write("我收到消息了".getBytes());
                    outputStream.flush();
                    Thread.sleep(200);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


}
