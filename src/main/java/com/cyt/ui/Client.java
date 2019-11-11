package com.cyt.ui;

import com.cyt.dao.IAccountDao;
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
        IAccountService as = (IAccountService) ac.getBean("accountService4");
//        IAccountService as2 = (IAccountService) ac.getBean("accountService4");
//        System.out.println(as == as2);
//        AccountServiceImpl2 as = ac.getBean("accountService2",AccountServiceImpl2.class);
//        IAccountService as = ac.getBean("accountService3", AccountServiceImpl3.class);
        System.out.println(as);
//        as.printService();

        IAccountDao adao1 = ac.getBean("accountDao1",IAccountDao.class);
//        IAccountDao adao2 = ac.getBean("accountDao2",IAccountDao.class);
        System.out.println("创建accountDao1" + adao1);
//        System.out.println("创建accountDao1" + adao2);
        as.saveAccount();
        ac.close();
    }
}
