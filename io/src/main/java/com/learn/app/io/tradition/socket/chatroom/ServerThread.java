package com.learn.app.io.tradition.socket.chatroom;

import java.io.*;
import java.net.Socket;

/**
 * @desc 服务器
 * @author ji_fei
 * @date  2019-07-27 19:28
 */
public class ServerThread extends Thread {

    private Socket socket;


    public ServerThread() {

    }

    public ServerThread(Socket socket) {
       this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String conent = reader.readLine();
                for (Socket socket : ChatServer.socketList) {
                    if (socket != this.socket) {
                        PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                        System.out.println(conent);
                        writer.println(conent);
                        writer.flush();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
