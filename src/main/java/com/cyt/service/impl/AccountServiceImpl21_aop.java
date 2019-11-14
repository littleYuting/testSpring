package com.cyt.service.impl;

import com.cyt.service.IAccountService2;
import org.springframework.stereotype.Service;

@Service("accountService21")
public class AccountServiceImpl21_aop implements IAccountService2 {

    public void saveAccount() {
        System.out.println("执行了保存");
//        int i = 1/0;
    }

    public void updateAccount(int i) {
        System.out.println("执行了更新"+i);
    }

    public int deleteAccount() {
        System.out.println("执行了删除");
        return 0;
    }
}
