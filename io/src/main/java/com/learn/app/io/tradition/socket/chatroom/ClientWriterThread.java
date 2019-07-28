package com.learn.app.io.tradition.socket.chatroom;

import java.io.*;
import java.net.Socket;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-27 19:38
 */
public class ClientWriterThread extends Thread {

    private Socket socket;

    private String name;

    public ClientWriterThread(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            while (true) {
                String str = reader.readLine();
                printWriter.println(name + "说：" + str);
                printWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
