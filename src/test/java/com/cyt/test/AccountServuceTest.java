package com.cyt.test;

import com.cyt.domain.Account1;
import com.cyt.service.IAccountService1;
import config.SpringConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 使用 Junit 单元测试
 * 1. 无显式 main 函数
 *      junit 集成了一个 main 方法，该方法会自动判断当前测试类中哪些方法有 @Test 注解，然后让有 Test 注解的方法执行；
 * 2. 独立于 spring 框架
 *      在执行测试方法时，junit 根本不知道是否使用 spring 框架，因此并不会自动读取配置文件/类创建的额spring核心容器
 * 3. 注入无效
 *      测试方法执行时无 IOC 容器，即便写了 Autowired 注解也无法实现注入
 * ------------- spring 整合 junit 的配置
 * 1. 导入 spring 整合 junit 的 jar 包(坐标)
 * 2. 使用 junit 提供的一个注解把原有的 main 方法替换，替换成 spring 提供的 @Runwith
 * 3. 告知 spring 的运行期，spring 和 ioc 创建是基于 xml 还是注解，并说明位置 @ContextConfiguration
 *      location: 指定 xml 文件袋额位置，加上 classpath 关键字，表示在类路径下
 *      classes：指定注解类所在位置
 *  Attention：当使用 spring 5.x 版本的时候，要求 junit 的 jar 必须是 4.12 及以上
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountServuceTest {

    @Autowired
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
        Account1 account1 = as.findAccountById(2);
        System.out.println(account1);
    }
    @Test
    public void testSave() {
        Account1 account1 = new Account1();
        account1.setName("test");
        account1.setMoney(12345f);
        as.saveAccount(account1);
    }
    @Test
    public void testUpdate() {
        Account1 account1 = as.findAccountById(1);
        account1.setName("update");
        as.updateAccount(account1);
    }
    @Test
    public void testDelete() {
        as.deleteAccount(3);
    }
}
