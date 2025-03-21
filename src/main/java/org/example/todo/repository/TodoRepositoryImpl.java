package org.example.todo.repository;

import org.example.todo.Entity.Todo;
import org.example.todo.dto.TodoResponseDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TodoRepositoryImpl implements TodoRepository{
    private final JdbcTemplate jdbcTemplate;

    public TodoRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TodoResponseDto saveTodo(Todo todo) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("todo").usingGeneratedKeyColumns("id");

        Map<String,Object> parameters = new HashMap<>();
        parameters.put("todo",todo.getTodo());
        parameters.put("name",todo.getName());
        parameters.put("password",todo.getPassword());
        parameters.put("uploadDate",todo.getUploadDate());


        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return new TodoResponseDto(todo.getTodo(),todo.getName(),todo.getUploadDate(),key.longValue());
    }
}
