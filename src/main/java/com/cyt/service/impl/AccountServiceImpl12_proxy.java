package com.cyt.service.impl;

import com.cyt.dao.IAccountDao1;
import com.cyt.domain.Account1;
import com.cyt.service.IAccountService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//被增强的业务方法service就是切入点
@Service("accountService12")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)// spring 注解实现事务管理
public class AccountServiceImpl12_proxy implements IAccountService1 {
    @Autowired
    private IAccountDao1 accountDao11;

    public void setAccountDao1(IAccountDao1 accountDao1) {
        this.accountDao11 = accountDao1;
    }

    public List<Account1> findAllAccount() {
        return accountDao11.findAllAccount();
    }

    public Account1 findAccountById(Integer accountId) {
        return accountDao11.findAccountById(accountId);
    }

    public void saveAccount(Account1 account1) {
        accountDao11.saveAccount(account1);
    }

    public void updateAccount(Account1 account1) {
        accountDao11.updateAccount(account1);
    }

    public void deleteAccount(Integer accountId) {
        accountDao11.deleteAccount(accountId);
    }
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void transfer(String sourceName, String targetName, Float money) {
        Account1 source = accountDao11.findAccountByName(sourceName);
        // 2.2. 根据名称查询转入账户；
        Account1 target = accountDao11.findAccountByName(targetName);
        // 2.3. 转出账户减钱；
        source.setMoney(source.getMoney()-money);
        // 2.4. 转入账户加钱；
        target.setMoney(target.getMoney()+money);
        // 2.5. 更新转出账户；
        accountDao11.updateAccount(source);
        int i = 1/0;
        // 2.6. 更新转入账户；
        accountDao11.updateAccount(target);
    }
}
