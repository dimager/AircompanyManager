package com.epam.jwd.service.impl;

import com.epam.jwd.dao.entity.User;
import org.mindrot.jbcrypt.BCrypt;

public class BcryptService {

    public static User crypt(User user){
        user.setSalt(BCrypt.gensalt());
        user.setPassword(BCrypt.hashpw(user.getPassword(),user.getSalt()));
        return user;
    }
    public static User hashPassword(User user){
        user.setPassword(BCrypt.hashpw(user.getPassword(),user.getSalt()));
        return user;
    }
}
