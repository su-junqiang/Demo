package com.test.demo.config.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

//标明告诉spring容器来到当前的类来检索相关的bean
//初始化时就会检索这个类的bean执行数据库的一些注入
@Configuration
// 配置mybatis mapper 的扫描路径
@MapperScan("com.test.demo.dao.mappers")
public class DataSourceConfiguration {
    @Value("${jdbc.driver}")
    private String jdbcDriver;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUser;
    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Bean(name = "dataSource")
    // 返回数据库连接池 ComboPooledDataSource
    public ComboPooledDataSource createDataSource() throws PropertyVetoException {
        // 生成实例
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        // 设置驱动
        dataSource.setDriverClass(jdbcDriver);
        //设置连接URL
        dataSource.setJdbcUrl(jdbcUrl);
        //设置用户名
        dataSource.setUser(jdbcUser);
        //设置密码
        dataSource.setPassword(jdbcPassword);
        //关闭连接后不自动提交commit
        dataSource.setAutoCommitOnClose(false);
        return dataSource;
    }
}
