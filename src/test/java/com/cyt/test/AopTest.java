package com.cyt.test;

import com.cyt.service.IAccountService;
import com.cyt.service.IAccountService1;
import com.cyt.service.IAccountService2;
import org.aspectj.lang.annotation.Around;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean_aop_annotation.xml")
public class AopTest {
    /**
     * 在 junit 中集成 spring @RunWith，通过 @ContextConfiguration 获得指定 xml 的 ioc 容器
     */
    @Autowired
    private IAccountService2 accountService21;
    @Autowired
    private IAccountService1 accountService11;

    @Test
    public void testAopAnnotation(){
        accountService21.saveAccount();
    }

    @Test
    public void testTransferAopAnnotation(){
        // 此测试是在学习了aop的基本知识之后，自己练习实现之前的银行账户转账，之前是用基于类的动态代理方法实现的， Proxy 类
        accountService11.transfer("bbb", "update", 100f);
    }
    /**
     * 使用 ClassPathXmlApplicationContext 获得 ioc 容器
     * @param args
     */
    public static void main(String[] args) {
        //1. 获取容器
//        ApplicationContext ac = new ClassPathXmlApplicationContext("bean_aop.xml");
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean_aop_annotation.xml");
        //2. 获取对象
//        IAccountService2 as = ac.getBean("accountService2",IAccountService2.class);
        IAccountService2 as = ac.getBean("accountService21",IAccountService2.class);
        //3. 执行方法
        as.saveAccount();
//        as.updateAccount(1);
//        as.deleteAccount();
    }

}
