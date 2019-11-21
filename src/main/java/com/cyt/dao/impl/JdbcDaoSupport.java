//package com.cyt.dao.impl;
//
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//
///**
// * 此类用于抽取 dao 类中的重复代码
// */
//public class JdbcDaoSupport {
//    private JdbcTemplate jt;
//    private DataSource ds;
//
//    public void getJdbcTemplate(JdbcTemplate jt) {
//        this.jt = jt;
//    }
//    public JdbcTemplate getJt() {
//        return jt;
//    }
//    public void setDataSource(DataSource ds) {
//        this.ds = ds;
//        if (jt == null) {
//            jt = createJdbcTemplate(ds);
//        }
//    }
//    public JdbcTemplate createJdbcTemplate(DataSource ds){
//        JdbcTemplate jt = new JdbcTemplate(ds);
//        return jt;
//    }
//}
