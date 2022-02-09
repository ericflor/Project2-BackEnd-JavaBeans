package com.revature.repos;

import java.util.List;

public interface DAO<T> {

    boolean save(T o);

    boolean update(T o);

    boolean delete(int id);

    List<T> getAll();

    T get(int id);
}