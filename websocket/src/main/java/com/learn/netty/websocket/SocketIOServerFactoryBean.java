package com.learn.netty.websocket;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @desc 生产 SocketIOServer 实例
 * @author ji_fei
 * @date  2019-07-15 14:58
 */
@Component
public class SocketIOServerFactoryBean implements FactoryBean<SocketIOServer> {

    @Override
    public SocketIOServer getObject() throws Exception {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);

        SocketIOServer server = new SocketIOServer(config);
        return server;
    }

    @Override
    public Class<?> getObjectType() {
        return SocketIOServer.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
