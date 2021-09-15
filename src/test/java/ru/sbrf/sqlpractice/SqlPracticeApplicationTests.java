package ru.sbrf.sqlpractice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sbrf.sqlpractice.dao.AuthorDaoJdbc;
import ru.sbrf.sqlpractice.models.Author;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тестирование AuthorDaoJdbc")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(AuthorDaoJdbc.class)
class SqlPracticeApplicationTests {

    @Autowired
    private AuthorDaoJdbc jdbc;

    @DisplayName("Выборка автора по id")
    @Test
    void testGetAuthorByID() {
        Author author = jdbc.getById(1);
        assertThat(author).hasFieldOrPropertyWithValue("firstName", "Гедеон");
    }

    @DisplayName("Успешная вставка нового автора")
    @Test
    void testInsertNewAuthor() {
        Author newAuthor = new Author(1, "TestFirstName", "TestSecondName");
        long id = jdbc.create(newAuthor);
        Author author = jdbc.getById(id);
        assertThat(author).hasFieldOrPropertyWithValue("firstName", "TestFirstName");
        assertThat(author).hasFieldOrPropertyWithValue("secondName", "TestSecondName");
    }

    @DisplayName("Обновление существующего автора")
    @Test
    void testUpdateExistedAuthor() {
        Author existsAuthor = new Author(1, "Гедеон", "Писатель");
        long id = jdbc.update(existsAuthor);
        Author author = jdbc.getById(id);
        assertThat(author).hasFieldOrPropertyWithValue("secondName", "Писатель");
    }

    @DisplayName("Удаление автора")
    @Test
    void testDeleteAuthor() {
        long id = jdbc.delete(1);
        SQLException throwables = assertThrows(SQLException.class, () -> jdbc.getById(id));
        assertEquals(throwables.getErrorCode(), 1);
    }
}
