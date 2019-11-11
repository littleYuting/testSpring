package com.cyt.service.impl;

import com.cyt.service.IAccountService;

import java.util.Date;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl2 implements IAccountService {
    // 如果是经常用的属性变量，不适用依赖注入的方法
    private String name;
    private Integer age;
    private Date birthday;

    public AccountServiceImpl2(){
        System.out.println("创建了一个 service2 对象");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
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
