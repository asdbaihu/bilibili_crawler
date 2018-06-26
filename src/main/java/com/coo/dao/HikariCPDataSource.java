package com.coo.dao;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.hibernate.HikariConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author: cooocy
 * @Date: 2018/6/26 14:46
 **/
public class HikariCPDataSource {

    final private static String username = "";
    final private static String password = "";
    final private static String driverClassName = "org.postgresql.Driver";
    final private static String jdbcUrl = "jdbc:postgresql://0.0.0.0:5432/db_bili";
    private static HikariDataSource hds;

    static {
        hds = new HikariDataSource();
        hds.setMaximumPoolSize(100);
        hds.setUsername(username);
        hds.setPassword(password);
        hds.setDriverClassName(driverClassName);
        hds.setJdbcUrl(jdbcUrl);
    }

    public static Connection getConnection() {
        try {
            return hds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
