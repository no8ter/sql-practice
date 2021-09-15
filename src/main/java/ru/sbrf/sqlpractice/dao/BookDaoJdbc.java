package ru.sbrf.sqlpractice.dao;

import ru.sbrf.sqlpractice.models.Book;

import java.util.List;

public class BookDaoJdbc implements Dao<Book> {
    @Override
    public Book getById(long id) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public long delete(long id) {
        return 0;
    }

    @Override
    public long create(Book book) {
        return 0;
    }

    @Override
    public long update(Book book) {
        return 0;
    }
}
