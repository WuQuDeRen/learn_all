package com.learn.app.io.tradition.socket.chatroom;

import com.google.common.collect.Lists;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-27 17:37
 */
public class ChatServer {

    public static List<Socket> socketList = Lists.newArrayList();

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8202);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println(socket.getInetAddress().getAddress() +":"+ socket.getPort() + ", 加入了聊天室");
            socketList.add(socket);
            new ServerThread(socket).start();
        }

    }




}
