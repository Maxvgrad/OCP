package org.ocp.jdbc;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.util.Properties;

public class BaseTest {

    DataSource dataSource;

    @BeforeEach
    protected void setUp() throws Exception {
        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL("jdbc:h2:mem:test;INIT=CREATE SCHEMA IF NOT EXISTS TEST\\;" +
                                      "RUNSCRIPT FROM 'classpath:scripts/init.sql';DB_CLOSE_ON_EXIT=FALSE");
        jdbcDataSource.setUser("sa");
        jdbcDataSource.setPassword("sa");

        dataSource = jdbcDataSource;
    }

    protected DataSource getDataSource() {
        return dataSource;
    }

    @Test
    void driverManager() throws Exception {
        DriverManager.getConnection("url", new Properties());
    }
}