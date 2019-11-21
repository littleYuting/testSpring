package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 该类是一个配置类，它的作用和 bean.xml 是一样的
 * spring 中的新注解
 * Configuration:
 *      作用：指定当前类是一个配置类
 *      细节：当配置类作为 AnnotationConfigApplicationContext 对象创建的参数时，该注解可用不写；
 * ComponentScan:
 *      作用：用于通过注解指定 spring 在创建容器时要扫描的包；
 *      属性：
 *          value：它和basePackages的作用是一样的，都是用于指定创建容器时要扫描的包
 *          使用此注解就相当于在 xml 中配置了：
 *          <context:component-scan base-package="com.cyt"></context:component-scan>
 * Bean
 *      作用：用于把当前方法的返回值作为 bean 对象存入 spring 的 ioc 容器中；
 *      属性：
 *          name：用于指定bean的id，当不写时，默认是当前方法的名称;
 *      细节：当我们使用注解配置方法时，如果方法有参数，spring框架会去容器中查找有没有可用的
 *      bean对象，查找方式和Autowired注解的作用是一样的；
 * Import
 *      作用：用于在主配置类中导入其他的配置类，实现效果和在 @ComponentScan({" "," ",..," "}) 一样
 *      属性:
 *          value：用于指定其他配置类的字节码
 *          当使用 Import 注解之后，有 Import 注解的类就是父配置类，而导入的都是子配置类。
 * PropertySource
 *      作用：用于指定 properties 文件的位置；
 *      属性：
 *          value：指定文件的名称和路径，其中关键字 classpath 表示类路径下
 */
@Configuration // 若在生成 ioc 容器时已传入该 config 的参数，则该注释可不写：ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
@ComponentScan("com.cyt")
@Import({JdbcConfig.class,TransactionConfig.class}) // @ComponentScan({"com.cyt","config"})
@PropertySource("classpath:jdbcConfig.properties")
@EnableTransactionManagement// 开启对事务管理的支持
public class SpringConfiguration {

    /**
     *
     * 用于创建一个 QueryRunner 对象
     * @param dataSource
     * @return
     */
    @Bean(name = "runner")
    @Scope("prototype")
    public QueryRunner createQueryRunner(DataSource dataSource){
        return new QueryRunner(dataSource);
    }

    /**
     * 创建数据源对象
     * @return
     */
    @Bean(name = "dataSource")
    public DataSource createDataSource(){
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/eesy");
            ds.setUser("root");
            ds.setPassword("1234");
            return ds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
