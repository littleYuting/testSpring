package com.cyt.service;

import com.cyt.domain.Account1;

import java.util.List;

/**
 * 账户的业务层接口
 */
public interface IAccountService1 {

    /**
     * 查询所有
     * @return
     */
    List<Account1> findAllAccount();

    /**
     * 查询一个
     * @return
     */
    Account1 findAccountById(Integer accountId);

    /**
     * 保存
     * @param
     */
    void saveAccount(Account1 account1);

    /**
     * 更新
     * @param
     */
    void updateAccount(Account1 account1);

    /**
     * 删除
     * @param
     */
    void deleteAccount(Integer accountId);

    /**
     * 转账
     * @param sourceName 转出账户名称
     * @param targetName 转入账户名称
     * @param money      转账金额
     */
    void transfer(String sourceName, String targetName, Float money);

}
