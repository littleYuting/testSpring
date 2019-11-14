package com.cyt.service.impl;

import com.cyt.dao.IAccountDao1;
import com.cyt.domain.Account1;
import com.cyt.service.IAccountService1;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//被增强的业务方法service就是切入点
public class AccountServiceImpl12_proxy implements IAccountService1 {

    private IAccountDao1 accountDao1;

    public void setAccountDao1(IAccountDao1 accountDao1) {
        this.accountDao1 = accountDao1;
    }

    public List<Account1> findAllAccount() {
        return accountDao1.findAllAccount();
    }

    public Account1 findAccountById(Integer accountId) {
        return accountDao1.findAccountById(accountId);
    }

    public void saveAccount(Account1 account1) {
        accountDao1.saveAccount(account1);
    }

    public void updateAccount(Account1 account1) {
        accountDao1.updateAccount(account1);
    }

    public void deleteAccount(Integer accountId) {
        accountDao1.deleteAccount(accountId);
    }

    public void transfer(String sourceName, String targetName, Float money) {
        Account1 source = accountDao1.findAccountByName(sourceName);
        // 2.2. 根据名称查询转入账户；
        Account1 target = accountDao1.findAccountByName(targetName);
        // 2.3. 转出账户减钱；
        source.setMoney(source.getMoney()-money);
        // 2.4. 转入账户加钱；
        target.setMoney(target.getMoney()+money);
        // 2.5. 更新转出账户；
        accountDao1.updateAccount(source);
//        int i = 1/0;
        // 2.6. 更新转入账户；
        accountDao1.updateAccount(target);
    }
}
