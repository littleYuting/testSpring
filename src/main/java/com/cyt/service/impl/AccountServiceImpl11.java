package com.cyt.service.impl;

import com.cyt.dao.IAccountDao1;
import com.cyt.domain.Account1;
import com.cyt.service.IAccountService1;
import com.cyt.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("accountService11")
public class AccountServiceImpl11 implements IAccountService1 {
    @Autowired
    private IAccountDao1 accountDao1;
    @Autowired
    private TransactionManager tsManager;// 其引入是为了保证单个连接，维持事务一致性

    public void setTsManager(TransactionManager tsManager) {
        this.tsManager = tsManager;
    }
    //   bean property 实现 类对象创建
//    public void setAccountDao(IAccountDao1 accountDao1) {
//        this.accountDao1 = accountDao1;
//    }

    public List<Account1> findAllAccount() {
        try {
            //1. 开启事务
            tsManager.beginConnection();
            //2. 执行操作
            List<Account1> account1s =  accountDao1.findAllAccount();
            //3. 提交事务
            tsManager.commit();
            //4. 返回结果
            return account1s;
        } catch (Exception e) {
            //5. 回滚操作
            tsManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //6. 释放资源
            tsManager.release();
        }
    }

    public Account1 findAccountById(Integer accountId) {
        try {
            //1. 开启事务
            tsManager.beginConnection();
            //2. 执行操作
            Account1 account1 =  accountDao1.findAccountById(accountId);
            //3. 提交事务
            tsManager.commit();
            //4. 返回结果
            return account1;
        } catch (Exception e) {
            //5. 回滚操作
            tsManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //6. 释放资源
            tsManager.release();
        }
    }

    public void saveAccount(Account1 account1) {
        try {
            //1. 开启事务
            tsManager.beginConnection();
            //2. 执行操作
            accountDao1.saveAccount(account1);
            //3. 提交事务
            tsManager.commit();
            //4. 返回结果
        } catch (Exception e) {
            //5. 回滚操作
            tsManager.rollback();
        } finally {
            //6. 释放资源
            tsManager.release();
        }
    }

    public void updateAccount(Account1 account1) {
        try {
            //1. 开启事务
            tsManager.beginConnection();
            //2. 执行操作
            accountDao1.updateAccount(account1);
            //3. 提交事务
            tsManager.commit();
            //4. 返回结果
        } catch (Exception e) {
            //5. 回滚操作
            tsManager.rollback();
        } finally {
            //6. 释放资源
            tsManager.release();
        }
    }

    public void deleteAccount(Integer accountId) {
        try {
            //1. 开启事务
            tsManager.beginConnection();
            //2. 执行操作
            accountDao1.deleteAccount(accountId);
            //3. 提交事务
            tsManager.commit();
            //4. 返回结果
        } catch (Exception e) {
            //5. 回滚操作
            tsManager.rollback();
        } finally {
            //6. 释放资源
            tsManager.release();
        }
    }

    public void transfer(String sourceName, String targetName, Float money) {
        try {
            //1. 开启事务
            tsManager.beginConnection();
            //2. 执行操作
            // 2.1. 根据名称查询转出账户；
            Account1 source = accountDao1.findAccountByName(sourceName);
            // 2.2. 根据名称查询转入账户；
            Account1 target = accountDao1.findAccountByName(targetName);
            // 2.3. 转出账户减钱；
            source.setMoney(source.getMoney()-money);
            // 2.4. 转入账户加钱；
            target.setMoney(target.getMoney()+money);
            // 2.5. 更新转出账户；
            accountDao1.updateAccount(source);
//            int i = 1/0;
            // 2.6. 更新转入账户；
            accountDao1.updateAccount(target);
            //3. 提交事务
            tsManager.commit();
            //4. 返回结果
        } catch (Exception e) {
            //5. 回滚操作
            tsManager.rollback();
            e.printStackTrace();
        } finally {
            //6. 释放资源
            tsManager.release();
        }

    }
}
