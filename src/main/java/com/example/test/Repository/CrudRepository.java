package com.example.test.Repository;

import java.util.List;

public interface CrudRepository<T, Tid> {
    boolean add(T item);
    boolean update(T item);
    boolean deleteById(Tid key);

    T getById(Tid key);
    List<T> getAll();
}
