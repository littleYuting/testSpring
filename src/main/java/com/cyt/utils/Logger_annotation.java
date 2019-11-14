package com.cyt.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 *  用于记录日志的工具类，它里面提供了公共的代码
 */
@Component("logger_annotation")
@Aspect//表明当前类是一个切面类
/**
 * aop before、after等 注解执行结果 如下：
 * 前置通知Logger类中的beforePrintLog方法开始记录日志啦
 * 执行了保存
 * 最终通知Logger类中的afterprintLog方法开始记录日志啦
 * 后置通知Logger类中的afterReturningPrintLog方法开始记录日志啦
 * 分析：最终通知和后置通知执行顺序有变，在实际开发中应慎重，如果采用环绕通知就不会出现此问题
 */
@EnableAspectJAutoProxy
public class Logger_annotation {

    @Pointcut("execution(* com.cyt.service.impl.AccountServiceImpl21_aop.saveAccount(..))")
    public void pt1(){}

//    @Before("pt1()")
    public void beforePrintLog(){
        System.out.println("前置通知Logger类中的beforePrintLog方法开始记录日志啦");
    }

//    @AfterReturning("pt1()")
    public void afterReturningPrintLog(){
        System.out.println("后置通知Logger类中的afterReturningPrintLog方法开始记录日志啦");
    }

//    @AfterThrowing("pt1()")
    public void afterThrowingPrintLog(){
        System.out.println("异常通知Logger类中的afterThrowingPrintLog方法开始记录日志啦");
    }

//    @After("pt1()")
    public void afterPrintLog(){
        System.out.println("最终通知Logger类中的afterprintLog方法开始记录日志啦");
    }

    @Around("pt1()")
    public Object aroundPrintLog(ProceedingJoinPoint pjp){
        Object returnValue = null;
        try {
            Object[] args = pjp.getArgs();
            beforePrintLog();
            returnValue = pjp.proceed(args);
            afterReturningPrintLog();
            return returnValue;
        } catch (Throwable t) {
            afterThrowingPrintLog();
            throw new RuntimeException(t);
        } finally {
            afterPrintLog();
        }
    }
}
