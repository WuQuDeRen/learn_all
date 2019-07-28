package com.learn.app.io.nio.chatroom;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.List;
import java.util.Set;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-27 17:37
 */
public class ServerChat {

    public static List<Socket> socketList = Lists.newArrayList();

    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8202));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        Selector clientSelector = Selector.open();


        new ServerThreadAcceptSelector(selector, clientSelector).start();

        new ServerThreadReadSelector(clientSelector).start();

    }




}
