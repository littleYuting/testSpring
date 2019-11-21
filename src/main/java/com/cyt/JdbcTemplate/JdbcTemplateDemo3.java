package com.cyt.JdbcTemplate;

import com.cyt.domain.Account1;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Jdbc Template 的 CRUD 操作
 */
public class JdbcTemplateDemo3 {
    public static void main(String[] args) {
        // 1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean_jdbcTemplate.xml");
        // 2.获取对象
        JdbcTemplate jt = ac.getBean("jdbcTemplate",JdbcTemplate.class);
//        jt.execute("insert into account(name,money)values('ddd',1115)");
        // 3.执行操作
        // 保存
//        jt.update("insert into account(name,money)values(?,?)","eee",268);
        // 更新
//        jt.update("update account set name=?,money=? ","test",1115);
        // 删除
//        jt.update("delete from account where id=?",8);
        // 查询所有
//        List<Account1> accounts = jt.query("select * from account where money > ?",new AccountRowMapper(),1000f);
        // 类似于 dbutils 中的 beanListHandler,由 spring 提供的，不需要手动写 AccountRowMapper
//        List<Account1> accounts = jt.query("select * from account where money > ?",new BeanPropertyRowMapper<Account1>(Account1.class),1000f);
//        for (Account1 account1:accounts) {
//            System.out.println(account1);
//        }
        // 查询一个
        List<Account1> accounts = jt.query("select * from account where money < ?",new BeanPropertyRowMapper<Account1>(Account1.class),1000f);
        System.out.println(accounts.isEmpty()?"empty":accounts.get(0));
        // 查询返回一行一列（使用聚合函数，但不加 group by 子句）
        System.out.println(jt.queryForObject("select count(*) from account where money > ?",Integer.class,1000f));

    }
}
class AccountRowMapper implements RowMapper<Account1> {
    /**
     * 把结果集中的数据封装到 Account 中，然后由 spring 把每个 Account 加到集合中国
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    public Account1 mapRow(ResultSet resultSet, int i) throws SQLException {
        Account1 account1 = new Account1();
        account1.setId(resultSet.getInt("id"));
        account1.setName(resultSet.getString("name"));
        account1.setMoney(resultSet.getFloat("money"));
        return account1;
    }
}
