package com.learn.netty.websocket;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-15 15:02
 */
@Component
public class MessageEventHandler {

    @Autowired
    private SocketIOServer socketIoServer;

    /***
     * 客户端链接的时候
     * @param client
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        String mac = client.getHandshakeData().getSingleUrlParam("mac");
        client.sendEvent("message", "onConnect back");
        System.out.println("客户端：" + client.getSessionId() + "已连接,mac=" + mac);
    }

    @OnDisconnect
    public void onDisConnect(SocketIOClient client) {
        System.out.println("客户端：" + client.getSessionId() + "断开连接");
    }

    @OnEvent("messageevent")
    public void onEvent(SocketIOClient client, AckRequest request, String data) {
        // 会发新消息
        client.sendEvent("messageevent", "我是服务器发送来的信息");

    }

}
