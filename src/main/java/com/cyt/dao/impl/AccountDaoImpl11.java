package com.cyt.dao.impl;

import com.cyt.dao.IAccountDao1;
import com.cyt.domain.Account1;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户的持久层实现类
 */
@Repository("accountDao11")
public class AccountDaoImpl11 implements IAccountDao1 {
    @Autowired
    private QueryRunner runner;

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    public List<Account1> findAllAccount() {
        try{
            return runner.query("select * from account", new BeanListHandler<Account1>(Account1.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Account1 findAccountById(Integer accountId) {
        try{
            return runner.query("select * from account where id = ?", new BeanHandler<Account1>(Account1.class), accountId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAccount(Account1 account1) {
        try{
            runner.update("insert into account(name,money) values(?,?)", account1.getName(),account1.getMoney());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAccount(Account1 account1) {
        try{
            runner.update("update account set name=?,money=? where id=?", account1.getName(),account1.getMoney(),account1.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAccount(Integer accountId) {
        try{
            runner.update("delete from account where id=?", accountId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
