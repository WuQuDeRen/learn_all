package com.learn.netty.websocket;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-15 14:55
 */
public class WebsocketInit implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private SocketIOServer socketIOServer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            socketIOServer.start();
        }
    }
}
