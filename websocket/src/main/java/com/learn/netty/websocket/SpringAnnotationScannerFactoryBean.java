package com.learn.netty.websocket;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @desc TODO
 * @author ji_fei
 * @date  2019-07-15 15:00
 */
@Component
public class SpringAnnotationScannerFactoryBean implements FactoryBean<SpringAnnotationScanner> {

    @Autowired
    private SocketIOServer socketIOServer;

    @Override
    public SpringAnnotationScanner getObject() throws Exception {
        return new SpringAnnotationScanner(socketIOServer);
    }

    @Override
    public Class<?> getObjectType() {
        return SpringAnnotationScanner.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
