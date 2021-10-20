package repository;

import repository.entity.Entity;

import java.util.List;

public interface Repository<K, T extends Entity>{
    boolean save (T entity);
    List<T> findAll();
    T findById(K id);
    boolean deleteById(K id);

}
