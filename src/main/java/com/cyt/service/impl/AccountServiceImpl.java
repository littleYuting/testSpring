package com.cyt.service.impl;

import com.cyt.service.IAccountService;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {
    // 如果是经常用的属性变量，不适用依赖注入的方法
    private String name;
    private Integer age;
    private Date birthday;

    public AccountServiceImpl(){
        System.out.println("创建了一个 service 对象");
    }

    public AccountServiceImpl(String name, Integer age, Date birthday){
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public void saveAccount() {
        System.out.println("service 中的 saveAccount 方法执行啦");
    }

    public void init(){
        System.out.println("service 对象创建啦");
    }

    public void destroy(){
        System.out.println("service 对象销毁啦");
    }

    public void printService(){
        System.out.println("service 中的属性 :"+this.name+" , "+this.age+" , "+this.birthday);
    }
}
