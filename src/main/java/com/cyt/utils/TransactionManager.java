package com.cyt.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 和事务管理相关的工具类，它包含了：开启事务、提交事务、回滚事务和释放连接，这个就是实现加强service的动态代理类
 * 事务控制是在业务层的，spring 中的事务控制是面向 Aop 的
 */
@Component("txManager")
@Aspect
@EnableAspectJAutoProxy
public class TransactionManager {
    /**
     * 以下案例，如果把环绕通知注掉，会报错：Can't call commit when autocommit=true，这是因为
     * 最终通知和后置通知执行顺序有变，提前释放了连接，导致无法 commit，在实际开发中应慎重，如果采用环绕通知就不会出现此问题;
     */
    @Autowired
    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    @Pointcut("execution(* com.cyt.service.impl.AccountServiceImpl11.transfer(..))")
    private void pointCut(){}

    /**
     * 开启事务
     */
//    @Before("pointCut()")
    public void beginConnection(){
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    @AfterReturning("pointCut()")
    public void commit(){
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    @AfterThrowing("pointCut()")
    public void rollback(){
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放连接
     */
    @After("pointCut()")
    public void release(){
        try {
            connectionUtils.getThreadConnection().close();
            connectionUtils.removeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Around("pointCut()")
//    public void aroundTansfer(ProceedingJoinPoint pjp){
//        Object[] args = pjp.getArgs();
//        try {
//            //1. 开启事务
//            beginConnection();//前置通知
//            //2. 执行操作
//            pjp.proceed(args); // 整个invoke方法在执行就是环绕通知，且环绕通知中有明确的切入点方法调用,必须 catch Throwable
//            //3. 提交事务
//            commit();//后置通知
//            //4. 返回结果
//        } catch (Throwable t) {
//            //5. 回滚操作
//            rollback(); //异常通知
//            throw new RuntimeException(t);
//        } finally {
//            //6. 释放资源
//            release(); //最终通知
//        }
//    }
}
