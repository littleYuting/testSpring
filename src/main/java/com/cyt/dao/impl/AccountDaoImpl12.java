package com.cyt.dao.impl;

import com.cyt.dao.IAccountDao1;
import com.cyt.domain.Account1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户的持久层实现类
 * 当继承了 JdbcDaoSupport 时就不适合用注解啦
 */
//@Repository
public class AccountDaoImpl12 extends JdbcDaoSupport implements IAccountDao1 {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    public Account1 findAccountByName(String accountName) {

        List<Account1> account1s = super.getJdbcTemplate().query("select * from account where name = ?", new BeanPropertyRowMapper<Account1>(Account1.class), accountName);
        if (account1s.isEmpty()) {
            return null;
        }
        if (account1s.size() > 0) {
            System.out.println("数据集结果不唯一");
        }
        return account1s.get(0);
    }

    public Account1 findAccountById(Integer accountId) {

        List<Account1> account1s = getJdbcTemplate().query("select * from account where id = ?", new BeanPropertyRowMapper<Account1>(Account1.class), accountId);
        return account1s.isEmpty()?null:account1s.get(0);
    }

    public void updateAccount(Account1 account1) {
        try{
            getJdbcTemplate().update("update account set name=?,money=? where id=?", account1.getName(),account1.getMoney(),account1.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Account1> findAllAccount() {
        return null;
    }

    public void saveAccount(Account1 account1) {

    }

    public void deleteAccount(Integer accountId) {

    }
}
