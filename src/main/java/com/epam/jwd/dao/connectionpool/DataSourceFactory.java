package com.epam.jwd.dao.connectionpool;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DataSourceFactory {
    private static final Logger logger = LogManager.getLogger(DataSourceFactory.class);
    private static MysqlDataSource mysqlProperties = null;

    private DataSourceFactory() {
    }

    public static MysqlDataSource getMysqlProperties() {
        logger.debug("getMysqlProperties method");
        if (Objects.isNull(mysqlProperties))
            initDataSource();
        return mysqlProperties;
    }

    private static void initDataSource() {
        logger.debug("initDataSource method");
        Properties properties = new Properties();
        try (InputStream inputStream = DataSourceFactory.class.getClassLoader().getResourceAsStream("database.properties")) {
            properties.load(inputStream);
            mysqlProperties = new MysqlDataSource();
            mysqlProperties.setURL(properties.getProperty("url"));
            mysqlProperties.setUser(properties.getProperty("user"));
            mysqlProperties.setPassword(properties.getProperty("password"));
            mysqlProperties.setCharacterEncoding(properties.getProperty("characterEncoding"));
        } catch (IOException | SQLException e) {
            logger.error(e);
        }
    }
}
