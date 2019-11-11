package com.cyt.dao;

import com.cyt.domain.Account1;

import java.util.List;

/**
 * 账户的持久层接口
 */
public interface IAccountDao1 {
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
}