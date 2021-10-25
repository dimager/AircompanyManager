package com.epam.jwd.connectioonpool;


import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DataSourceFactory {
    private static MysqlDataSource dataSource = null;

    public static MysqlDataSource getMysqlDataSource() {
        if (Objects.isNull(dataSource))
            initDataSource();
        return dataSource;
    }

    private static void initDataSource() {
        Properties properties = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream("src/main/resources/database.properties");
            properties.load(inputStream);
            dataSource = new MysqlDataSource();
            dataSource.setURL(properties.getProperty("url"));
            dataSource.setUser(properties.getProperty("user"));
            dataSource.setPassword(properties.getProperty("password"));
            dataSource.setCharacterEncoding(properties.getProperty("characterEncoding"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
