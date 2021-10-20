package repository.impl;

import repository.Repository;
import repository.entity.Role;

import java.util.List;

public class RoleRepositoryImpl implements Repository<Byte, Role> {
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
