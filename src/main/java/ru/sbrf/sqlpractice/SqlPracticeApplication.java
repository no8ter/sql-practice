package ru.sbrf.sqlpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.sbrf.sqlpractice.dao.AuthorDaoJdbc;

@SpringBootApplication
public class SqlPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqlPracticeApplication.class, args);
    }

}
