package com.cyt.factory;

import com.cyt.dao.IAccountDao1;
import com.cyt.domain.Account1;
import com.cyt.service.IAccountService1;
import com.cyt.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于创建 Service 的代理对象的工厂
 */
@Component
public class BeanFactory {

    private IAccountService1 accountService1;
    private TransactionManager tsManager;

    public void setAccountService1(IAccountService1 accountService1) {
        this.accountService1 = accountService1;
    }

    public void setTsManager(TransactionManager tsManager) {
        this.tsManager = tsManager;
    }

    /**
     * 获取 Service 代理对象
     * 优势：
     *      减少重复代码
     *      提高开发效率
     *      维护方便（若TransactionManager中的方法改变，只需修改bean下对应的方法，否则需要在service中对所有被调用的都要做修改）
     * @return
     */
    public IAccountService1 getAccountService1() {
        // 织入：把增强应用到目标对象来创建新的代理对象的过程
        IAccountService1 asProxy = (IAccountService1) Proxy.newProxyInstance(accountService1.getClass().getClassLoader(),
                accountService1.getClass().getInterfaces(),
                //切面（Aspect）是切入点和通知（引介）的结合
                new InvocationHandler() {
                    /**
                     * 添加事务的支持
                     * @param proxy
                     * @param method
                     * @param args
                     * @return
                     * @throws Throwable
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object returnValue = null;
                        try {
                            //1. 开启事务
                            tsManager.beginConnection();//前置通知
                            //2. 执行操作
                            returnValue = method.invoke(accountService1, args); // 整个invoke方法在执行就是环绕通知，且环绕通知中有明确的切入点方法调用
                            //3. 提交事务
                            tsManager.commit();//后置通知
                            //4. 返回结果
                            return returnValue;
                        } catch (Exception e) {
                            //5. 回滚操作
                            tsManager.rollback(); //异常通知
                            throw new RuntimeException(e);
                        } finally {
                            //6. 释放资源
                            tsManager.release(); //最终通知
                        }
                    }
                });
        return asProxy;
    }
}
