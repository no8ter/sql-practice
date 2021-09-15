package ru.sbrf.sqlpractice.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.sbrf.sqlpractice.models.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements Dao<Author> {

    private final NamedParameterJdbcOperations jdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Author getById(long id) {
        Map<String, Long> map = new HashMap<>(1);
        map.put("id", id);
        return jdbc.queryForObject("SELECT * FROM AUTHOR WHERE id = :id",
                map, new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return null;
    }

    @Override
    public long delete(long id) {
        Map<String, Long> map = new HashMap<>(2);
        map.put("id", id);
        jdbc.update("DELETE FROM AUTHOR WHERE id = :id", map);
        return id;
    }

    @Override
    public long create(Author author) {
        Map<String, String> map = new HashMap<>(2);
        map.put("first_name", author.getFirstName());
        map.put("second_name", author.getSecondName());
        jdbc.update("insert into AUTHOR(" +
                        "first_name, second_name)" +
                        "VALUES(" +
                        ":first_name," +
                        ":second_name);", map);
        return jdbc.query("SELECT MAX(id) FROM AUTHOR;", (resultSet, i) -> resultSet.getLong(1)).get(0);
    }

    @Override
    public long update(Author author) {
        Map<String, String> map = new HashMap<>(3);
        map.put("id", String.valueOf(author.getId()));
        map.put("first_name", author.getFirstName());
        map.put("second_name", author.getSecondName());
        jdbc.update("UPDATE AUTHOR SET first_name = :first_name, second_name = :second_name " +
                        "WHERE id = :id;", map);
        return author.getId();
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Author(resultSet.getLong("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("second_name"));
        }
    }
}
