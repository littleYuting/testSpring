package com.cyt.dao.impl;

import com.cyt.dao.IAccountDao;
import org.springframework.stereotype.Repository;

/**
 * 账户持久层实现类
 */
@Repository("accountDao2")
public class AccountDaoImpl2 implements IAccountDao {

    public void saveAccount() {
        System.out.println("保存了账户2222");
    }
}
