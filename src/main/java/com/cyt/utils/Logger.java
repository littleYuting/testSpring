package com.cyt.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 *  用于记录日志的工具类，它里面提供了公共的代码
 */
public class Logger {

    /**
     * 前置通知：在切入点方法执行之前执行
     */
    public void beforePrintLog(){
        System.out.println("前置通知Logger类中的beforePrintLog方法开始记录日志啦");
    }

    /**
     * 后置通知：在切入点方法正常运行后执行
     */
    public void afterReturningPrintLog(){
        System.out.println("后置通知Logger类中的afterReturningPrintLog方法开始记录日志啦");
    }

    /**
     * 异常通知：在切入点方法运行产生异常后执行
     */
    public void afterThrowingPrintLog(){
        System.out.println("异常通知Logger类中的afterThrowingPrintLog方法开始记录日志啦");
    }

    /**
     * 最终通知：无论切入点方法是否正常运行都会在后面执行
     */
    public void afterPrintLog(){
        System.out.println("最终通知Logger类中的afterprintLog方法开始记录日志啦");
    }

    /**
     * 环绕通知
     * 问题：当配置了环绕通知后，切入点方法没有执行，而通知方法执行啦
     * 分析：通过对比动态代理中的环绕通知代码，发现动态代理的环绕通知有明确的切入点方法调用，而我们的代码中没有
     * 解决：
     *      Spring 框架为我们提供了一个接口，ProceedingJoinPoint，该接口有一个方法 proceed()，此方法就相当于明确调用切入点方法
     *      该接口可以作为环绕通知的方法参数，在程序执行时，spring 框架会为我们提供该接口的实现类供我们使用
     * 分析：spring 的环绕通知就是提供了可以处理不同通知消息顺序的一个接口
     */
    public Object aroundPrintLog(ProceedingJoinPoint pjp){
        Object returnValue = null;
        try {
            Object[] args = pjp.getArgs();
            System.out.println("环绕通知Logger类中的 aroundPrintLog 方法开始记录日志啦--前置");
            returnValue = pjp.proceed(args);
            System.out.println("环绕通知Logger类中的 aroundPrintLog 方法开始记录日志啦--后置");
            return returnValue;
        } catch (Throwable t) {
            System.out.println("环绕通知Logger类中的 aroundPrintLog 方法开始记录日志啦--异常");
            throw new RuntimeException(t);
        } finally {
            System.out.println("环绕通知Logger类中的 aroundPrintLog 方法开始记录日志啦--最终");
        }
    }
}
