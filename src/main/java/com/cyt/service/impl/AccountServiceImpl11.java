package com.cyt.service.impl;

import com.cyt.dao.IAccountDao1;
import com.cyt.domain.Account1;
import com.cyt.service.IAccountService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("accountService11")
public class AccountServiceImpl11 implements IAccountService1 {
    @Autowired
    private IAccountDao1 accountDao1;
//   bean property
//    public void setAccountDao(IAccountDao1 accountDao1) {
//        this.accountDao1 = accountDao1;
//    }

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
}
