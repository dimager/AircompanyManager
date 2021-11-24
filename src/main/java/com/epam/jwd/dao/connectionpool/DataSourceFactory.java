package com.epam.jwd.dao.connectionpool;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DataSourceFactory {
    private static MysqlDataSource mysqlProperties = null;

    private DataSourceFactory() {
    }

    public static MysqlDataSource getMysqlProperties() {
        if (Objects.isNull(mysqlProperties))
            initDataSource();
        return mysqlProperties;
    }

    private static void initDataSource() {
        Properties properties = new Properties();
        try (InputStream inputStream = DataSourceFactory.class.getClassLoader().getResourceAsStream("database.properties")) {
            properties.load(inputStream);
            mysqlProperties = new MysqlDataSource();
            mysqlProperties.setURL(properties.getProperty("url"));
            mysqlProperties.setUser(properties.getProperty("user"));
            mysqlProperties.setPassword(properties.getProperty("password"));
            mysqlProperties.setCharacterEncoding(properties.getProperty("characterEncoding"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
