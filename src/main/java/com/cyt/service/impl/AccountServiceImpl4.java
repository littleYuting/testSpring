package com.cyt.service.impl;

import com.cyt.dao.IAccountDao;
import com.cyt.dao.impl.AccountDaoImpl1;
import com.cyt.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.Date;

/**
 * 账户的业务层实现类
 * 曾经 xml 的配置：
 * <bean id="accountDao" class="com.cyt.dao.impl.AccountDaoImpl"
 * scope="" init-method="" destroy-method="">
 *      <property name="" value=""/ref=""></property>
 * </bean>
 * 注解：
 * 1）用于创建类对象并存入spring容器中（类似于 xml bean）：
 *      Component(value="指定bean的id")，默认value是当前类名，其首字母改小写
 *      Controller:一般用于表现层
 *      Service：一般用于业务层
 *      Repository：一般用于持久层
 *      这三个注解与component实现功能一致，只不过是spring框架对应三层使用的一个标志区分
 * 2）注入数据（类似于 bean property）；
 *      Autowired：
 *          action：自动按照类型注入，只要容器中有唯一的一个bean对象类型和要注入的变量类型匹配，就可以注入成功
 *                  如果ioc容器没有任何bean的类型和要注入的变量类型匹配，则报错
 *                  如果ioc容器中有多个类型匹配时，首先按照value类型圈出匹配的对象集合，然后根据变量名称id确定唯一的对象
 *          target：可以是变量上，也可以是方法上
 *      Qualifier（不能独立用，必须和Autowired一起，否则会报空指针异常）:
 *          作用：在按照类中注入的基础之上再按照名称注入，它在给类成员注入时不能单独使用，但是在给方法参数注入时可以
 *          属性：用于指定注入bean的id
 *      Resource(不依赖于 Autowired)
 *          作用：直接按照 bean 的 id 注入，可以独立使用
 *          属性：name，用于指定 bean 的 id
 *      以上三个注入都只能注入其他 bean 类型的数据，而基本类型和 String 类型无法使用上述注解实现，另外，
 *      集合类型的注入只能通过 xml 来实现。
 *
 *      value：
 *          作用：用于注入基本类型和String类型的数据
 *          属性：
 *              value：用于指定数据的值，可以使用 Spring 中 spEL（即 spring 的 el 表达式）
 *              spEL的写法：$(表达式)
 * 3）改变作用范围(bean scope)；
 *      scope 属性：value：指定范围的取值，常用取值：singleton（scope 默认是单例对象） prototype
 * 4）生命周期（bean init_method 、destroy_method）
 *      PostConstruct 作用：用于指定初始化方法
 *      PreDestroy 作用：用于指定销毁方法
 */
@Component(value = "accountService4")
//@Scope("prototype")
public class AccountServiceImpl4 implements IAccountService {
    /**
     *  注入的时候，首先会根据 类型匹配 ioc 的 value，若有多个，便会依据属性变量匹配  repository 的注解 name
     */
//    @Autowired
//    @Qualifier("accountDao1")
    @Resource(name = "accountDao2")
    private IAccountDao accountDao;// 报错：Caused by: org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.cyt.dao.IAccountDao' available: expected single matching bean but found 2: accountDaoImpl1,accountDaoImpl2
//    private IAccountDao accountDao1;
//    private IAccountDao accountDao2;// 依赖注释的话，目前如果把把多个 IAccountDao 实现类做属性，会没办法区分，报空指针异常

    public AccountServiceImpl4(){
        System.out.println("创建了一个 service4 对象");
    }
//    public AccountServiceImpl4(IAccountDao accountDao1, IAccountDao accountDaoImpl2){
//        this.accountDao1 = accountDao1;
//        this.accountDao2 = accountDaoImpl2;
//        System.out.println("创建了一个有参(accountDao)的 service4 对象");
//    }
    public void saveAccount() {
        accountDao.saveAccount();
//        accountDao2.saveAccount();
//        System.out.println("service4 中的 saveAccount 方法执行啦");
    }
    @PostConstruct
    public void init() {
        System.out.println("初始化方法执行啦");
    }
    @PreDestroy
    public void destroy() {
        System.out.println("销毁方法执行啦");
    }

//    public void setAccountDao(IAccountDao accountDao) {
//        this.accountDao = accountDao;
//    }
}
