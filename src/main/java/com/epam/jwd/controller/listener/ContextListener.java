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
        logger.debug("contextInitialized method");
        ConnectionPoolImpl.getInstance().init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.debug("contextDestroyed method");
        ConnectionPoolImpl.getInstance().shutdown();

    }
}
