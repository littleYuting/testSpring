package com.cyt.ui;

import com.cyt.service.IAccountService;
import com.cyt.service.impl.AccountServiceImpl;
import com.cyt.service.impl.AccountServiceImpl2;
import com.cyt.service.impl.AccountServiceImpl3;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {

    /**
     * 获取 spring 的 Ioc 核心容器，并根据 id 获取对象
     * @param args
     */
    public static void main(String[] args) {
        // 1. 从核心容器获取容器对象
//        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

        // 2. 根据 id 获取 bean 对象
//        IAccountService as = (IAccountService) ac.getBean("accountService");
//        AccountServiceImpl2 as = ac.getBean("accountService2",AccountServiceImpl2.class);
        IAccountService as = ac.getBean("accountService3", AccountServiceImpl3.class);
        as.saveAccount();
//        as.printService();
//        ac.close();
    }
}
