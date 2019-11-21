package dynamic_proxy.proxy;

/**
 * 一个生产者
 * 实现过程中遇到的 bug：
 * 若不实现 IProducer 的接口会报错：Exception in thread "main" java.lang.ClassCastException: com.sun.proxy.$Proxy0 cannot be cast to com.cyt.proxy
 * 原因：被代理的类没有继承接口，而是继承了一个基类
 * 问题：为什么动态代理必须针对接口
 * 答：JDK 动态代理的原理就是根据定义好的规则，用传入的接口创建一个新类
 */

public class Producer implements IProducer {

    /**
     * 销售
     * @param money
     */
    public void saleProduct(float money){
        System.out.println("销售产品，并拿到钱"+money);
    }

    /**
     * 售后
     * @param money
     */
    public void afterService(float money){
        System.out.println("提供售后服务，并拿到钱"+money);
    }
}
