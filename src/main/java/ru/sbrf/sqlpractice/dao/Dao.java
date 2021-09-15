package ru.sbrf.sqlpractice.dao;

import java.util.List;

public interface Dao<T> {

    T getById(long id);

    List<T> getAll();

    long delete(long id);

    long create(T t);

    long update(T t);

}
