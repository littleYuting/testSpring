package com.cyt.utils;

import config.JdbcConfig;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 *  连接的工具类，它用于从数据源中获取一个连接，并且实现和线程的绑定
 *  (主要解决的是在一个函数中多连接+中间异常造成的事务不一致问题)
 */
@Component
public class ConnectionUtils {

    private ThreadLocal<Connection> t1 = new ThreadLocal<Connection>();
    @Autowired
    private DataSource dataSource;

    public Connection getThreadConnection(){
        try{
            //1. 先从ThreadLocal上获取
            Connection conn = t1.get();
            //2. 判断当前线程上是否有连接
            if (conn == null) {
                //3. 从数据源中获取一个连接，并且存入ThreadLocal中
                conn = dataSource.getConnection();
                t1.set(conn);
            }
            //4. 返回当前线程上的连接
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  把连接和线程解绑
     */
    public void removeConnection(){
        t1.remove();
    }
}
