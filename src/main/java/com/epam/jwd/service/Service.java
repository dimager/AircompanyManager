package com.epam.jwd.service;

import com.epam.jwd.entity.Entity;

import java.util.List;

public interface Service <T extends Entity> {
    List<T> findAll();

}
