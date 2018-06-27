package com.coo.dao;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author: cooocy
 * @Date: 2018/6/26 14:46
 **/
public class HikariCPDataSource {

    public static Logger logger = Logger.getLogger(HikariCPDataSource.class);

    final private static String username = "";
    final private static String password = "";
    final private static String driverClassName = "org.postgresql.Driver";
    final private static String jdbcUrl = "";
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
            logger.error("ERROR:    ", e);
            return null;
        }
    }
}
