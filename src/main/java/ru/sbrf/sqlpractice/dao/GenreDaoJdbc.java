package ru.sbrf.sqlpractice.dao;

import ru.sbrf.sqlpractice.models.Genre;

import java.util.List;

public class GenreDaoJdbc implements Dao<Genre> {
    @Override
    public Genre getById(long id) {
        return null;
    }

    @Override
    public List<Genre> getAll() {
        return null;
    }

    @Override
    public long delete(long id) {
        return 0;
    }

    @Override
    public long create(Genre genre) {
        return 1;
    }

    @Override
    public long update(Genre genre) {
        return 0;
    }
}
