package com.learn.app.io.tradition.socket.chatroom;

import java.io.IOException;
import java.net.Socket;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-27 17:37
 */
public class ChatClient2 {

    public static void main(String[] args) throws IOException {

        Socket socket =  new Socket("127.0.0.1", 8202);
        new ClientWriterThread(socket, "小已").start();
        new ClientReaderThread(socket).start();
    }


}
