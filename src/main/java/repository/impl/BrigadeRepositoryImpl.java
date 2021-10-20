package repository.impl;

import repository.Repository;
import repository.entity.Brigade;

import java.util.List;

public class BrigadeRepositoryImpl implements Repository<Long, Brigade> {
    @Override
    public boolean save(Brigade entity) {
        return false;
    }

    @Override
    public List<Brigade> findAll() {
        return null;
    }

    @Override
    public Brigade findById(Long id) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
