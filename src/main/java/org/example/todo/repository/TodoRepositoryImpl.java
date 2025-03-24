package org.example.todo.repository;

import org.example.todo.Entity.Todo;
import org.example.todo.dto.TodoResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TodoRepositoryImpl implements TodoRepository {
    private final JdbcTemplate jdbcTemplate;

    public TodoRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TodoResponseDto saveTodo(Todo todo) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("todo").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("todo", todo.getTodo());
        parameters.put("name", todo.getName());
        parameters.put("password", todo.getPassword());
        parameters.put("uploadDate", todo.getUploadDate());
        parameters.put("editDate", todo.getEditDate());


        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return new TodoResponseDto(key.longValue(), todo.getTodo(), todo.getName(), todo.getUploadDate(), todo.getEditDate());
    }

    @Override
    public Todo findTodoById(Long id) {
        List<Todo> result = jdbcTemplate.query("select * from todo where id =?", todoRowMapper(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id 가 없습니다" + id));
    }

    @Override
    public List<TodoResponseDto> findConditionTodo(Date date, String name) {

        List<Todo> result = jdbcTemplate.query("SELECT * FROM todo WHERE editDate = ? OR name = ?",
                todoRowMapper(), date, name);

        return result.stream()
                .map(todo -> new TodoResponseDto(
                        todo.getId(),
                        todo.getTodo(),
                        todo.getName(),
                        todo.getUploadDate(),
                        todo.getEditDate()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public int updateTodo(Long id, String todo, String name) {
        if (todo == null) {
            return jdbcTemplate.update("update todo set  name= ? where id = ?", name, id);
        } else {
            return jdbcTemplate.update("update todo set  todo =? where id = ?", todo, id);
        }
    }

    private RowMapper<Todo> todoRowMapper() {
        return new RowMapper<Todo>() {
            @Override
            public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Todo(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("name"),
                        rs.getDate("uploadDate"),
                        rs.getDate("editDate")
                );
            }
        };
    }
}
