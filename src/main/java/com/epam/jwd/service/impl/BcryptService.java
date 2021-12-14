package com.epam.jwd.service.impl;

import com.epam.jwd.dao.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

public class BcryptService {
    private static final Logger logger = LogManager.getLogger(BcryptService.class);

    /**
     * Allows crypting username password and set user salt
     * @param user
     */
    public static void crypt(User user){
        logger.debug("crypt method");
        user.setSalt(BCrypt.gensalt());
        user.setPassword(BCrypt.hashpw(user.getPassword(),user.getSalt()));
    }

    /**
     * Allows hashing user password
     * @param user
     */
    public static void hashPassword(User user){
        logger.debug("hashPassword method");
        user.setPassword(BCrypt.hashpw(user.getPassword(),user.getSalt()));
    }
}
