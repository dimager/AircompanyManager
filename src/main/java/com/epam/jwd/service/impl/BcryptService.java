package com.epam.jwd.service.impl;

import com.epam.jwd.dao.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

public class BcryptService {
    private static final Logger logger = LogManager.getLogger(BcryptService.class);
    public static User crypt(User user){
        logger.debug("crypt method");
        user.setSalt(BCrypt.gensalt());
        user.setPassword(BCrypt.hashpw(user.getPassword(),user.getSalt()));
        return user;
    }
    public static User hashPassword(User user){
        logger.debug("hashPassword method");
        user.setPassword(BCrypt.hashpw(user.getPassword(),user.getSalt()));
        return user;
    }
}
