## 20191108-20191114 学习思路整理
==**关键词：java 注解，配置类，动态代理，AOP**==


### 1. bean 类加注解
- 在一个 bean 类上加注解，包括 bean 的 type（@Component、Service、Controller、Repository）、scope（singleton、prototype）、life-cicle（init、destroy），注入bean对象的注解（Authoride、Prolifier、Resource）
- 注：对于除 bean 以外的数据类型（基本数据类型、集合等），其注入只能通过 xml 配置，详见 bean.xml；

### 2. 银行账户业务的简单实现
### 2.1. 任务描述
- 针对 mySql 数据库中的 account 表进行简单操作 curd ，包括查询（findAll ，findSingle）、保存、更新、删除、转账，并学习用 Junit 进行测试
### 2.2. 实现
#### 2.2.1 bean.xml 配置 bean
-  对应 account 表有一个简单 java 类；
-  （ds）数据 bean ：dataSource（ComboPooledDataSource、jdbc）说明连接驱动、连接 url，用户名及密码；
-  （runner）数据操作 bean ：在 dao 层注入数据 bean ，runner.query(sql 语句) & runner.update(sql 语句)

#### 2.2.2 配置类
- 作用：通过非 xml 的方式创建 dataSource 和 runner bean；
- 引入 jdbcConfig.properties 保存建立连接的数据库信息，方便修改，jdbcConfig.java 中 dataSource 的 value 通过 $ 对 .properties 中的参数调用，eg @Value("${jdbc.url}")；
- 将配置类放入容器：@Config  @ComponentScan；
- 主(springConfig.java)从(jdbcConfig.java)配置类： @Import(从属配置类的类名) @PropertySource(从属配置类调用参数的路径) ；
- 配置类创建的 bean 对象：@Bean @Scope)；

#### 2.2.3 spring 整合 junit 的配置
- 说明：junit 单元测试独立于 spring 框架，无显示 main 函数，也无法仅依靠 @Authorid 实现 bean 注入
- 实现：
    - pom.xml 中 jar 包导入        

    ```
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-test</artifactId>
                <version>5.0.2.RELEASE</version>
            </dependency>)
    ```  

    - @Runwith 替换隐式 main(),将 Junit 与 spring 绑定；
    - @ContextConfiguration(location-bean.xml；classes-SpringConfiguration.class) 创建指定路径下的 ioc 容器；
    
#### 2.2.4 模拟转账业务
- 场景：
    - 多连接 + 中间异常 引发数据一致性问题：单连接（连接池，事务管理，如开启、提交、回滚、释放连接等）；
    - 多个 service 执行相同的事务管理操作造成代码重复：公有代理类；
- 解决方法
    - 方案1（从数据源中获取一个连接并与线程绑定）
        - 连接工具类 ConnectionUtils + 事务管理工具类 TranctionManager
        - 缺点：谁用谁调用，重复代码； 

    - 方案2（动态代理）
        - case 学习（详见 dynamic_proxy）：
            - 基于接口的动态代理（jdk Proxy.newProxyInstance()）,要求必须实现接口；
            - 基于类的动态代理（Enhancer.create()）；
        - 实现 ：采用基于接口的动态代理方法创建 BeanFactory.java，注意：需在 bean_proxy.xml 中采用工厂创建 bean 方式配置 beanFactory 产生的加强后的 service 类，且最后调用的就是这个经过加强的类；
    - 方案3（AOP 对方案2 的 spring 实现）
        - 学习 java AOP 概念，如前置、后置、异常、最终、环绕通知，切点方法,切入点表达式的写法等；
        - 实现：
            - 在 bean_aop.xml 中配置 <aop:config pointCut aspect> 标签，区分环绕通知与其他通知的区别；
            - 注解（@EnableAspectJAutoProxy，@PointCut、@Ater、@afterReturning、@afterThrowing、@After、@Around）
