package com.epam.jwd.controller.listener;

import com.epam.jwd.dao.connectionpool.impl.ConnectionPoolImpl;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPoolImpl.getInstance().init();
        logger.info("init pool");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPoolImpl.getInstance().shutdown();
        logger.info("destroy pool");

    }
}
