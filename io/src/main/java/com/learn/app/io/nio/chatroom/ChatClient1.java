package com.learn.app.io.nio.chatroom;

import java.io.IOException;
import java.net.Socket;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-27 17:37
 */
public class ChatClient1 {

    public static void main(String[] args) throws IOException {

        Socket socket =  new Socket("127.0.0.1", 8202);
        // 这个线程负责写
        new ClientWriterThread(socket, "小甲").start();
        // 这个线程负责读写
        new ClientReaderThread(socket).start();
    }


}
