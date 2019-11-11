package com.cyt.test;

import com.cyt.domain.Account1;
import com.cyt.service.IAccountService1;
import config.SpringConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 使用 Junit 单元测试
 * 1. 无显式 main 函数
 *      junit 集成了一个 main 方法，该方法会自动判断当前测试类中哪些方法有 @Test 注解，然后让有 Test 注解的方法执行；
 * 2. 独立于 spring 框架
 *      在执行测试方法时，junit 根本不知道是否使用 spring 框架，因此并不会自动读取配置文件/类创建的额spring核心容器
 * 3. 注入无效
 *      测试方法执行时无 IOC 容器，即便写了 Autowired 注解也无法实现注入
 */
public class AccountServuceTest {

    private ApplicationContext ac;
    private IAccountService1 as;

//    @Before// 会报空指针异常
//    public void init(){
//        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
//        IAccountService1 as = ac.getBean("accountService11",IAccountService1.class);
//    }

    @Test
    public void testFindAll() {
//        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
//        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
//        IAccountService1 as = ac.getBean("accountService11",IAccountService1.class);
        List<Account1> account1s = as.findAllAccount();
        for (Account1 account1:account1s) {
            System.out.println(account1);
        }
    }
    @Test
    public void testFindOne() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        IAccountService1 as = ac.getBean("accountService11",IAccountService1.class);
        Account1 account1 = as.findAccountById(2);
        System.out.println(account1);
    }
    @Test
    public void testSave() {
        Account1 account1 = new Account1();
        account1.setName("test");
        account1.setMoney(12345f);
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        IAccountService1 as = ac.getBean("accountService11",IAccountService1.class);
        as.saveAccount(account1);
    }
    @Test
    public void testUpdate() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        IAccountService1 as = ac.getBean("accountService11",IAccountService1.class);
        Account1 account1 = as.findAccountById(1);
        account1.setName("update");
        as.updateAccount(account1);
    }
    @Test
    public void testDelete() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        IAccountService1 as = ac.getBean("accountService11",IAccountService1.class);
        as.deleteAccount(3);
    }
}
