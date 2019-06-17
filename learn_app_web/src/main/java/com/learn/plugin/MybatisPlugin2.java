package com.learn.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Properties;

@Intercepts(
       @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
)
public class MybatisPlugin2 implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler target = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = SystemMetaObject.forObject(target);
        while (metaStatementHandler.hasGetter("h")) {
            Object h = metaStatementHandler.getValue("h");
            metaStatementHandler = SystemMetaObject.forObject(h);
            if (metaStatementHandler.hasGetter("target")) {
                Object nextTarget = metaStatementHandler.getValue("target");
                metaStatementHandler = SystemMetaObject.forObject(nextTarget);
            }
        }
        System.out.println(metaStatementHandler.getValue("delegate.boundSql.sql"));
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
