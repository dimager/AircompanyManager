package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.BaseDao;
import com.epam.jwd.entity.Role;

import java.util.List;

public class RoleDaoImpl implements BaseDao<Byte, Role> {

    @Override
    public boolean save(Role entity) {
        return false;
    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public Role findById(Byte id) {
        return null;
    }

    @Override
    public boolean deleteById(Byte id) {
        return false;
    }
}
