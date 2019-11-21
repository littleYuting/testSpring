package com.cyt.JdbcTemplate;

import com.cyt.dao.IAccountDao1;
import com.cyt.domain.Account1;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcTemplateDemo4 {
    public static void main(String[] args) {

        ApplicationContext ac = new ClassPathXmlApplicationContext("bean_jdbcTemplate.xml");
        IAccountDao1 accountDao = ac.getBean("accountDao12", IAccountDao1.class);
        Account1 account1 = accountDao.findAccountById(2);
        System.out.println(account1);
    }
}
