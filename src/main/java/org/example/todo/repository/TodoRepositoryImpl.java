package org.example.todo.repository;

import org.example.todo.Entity.Todo;
import org.example.todo.dto.TodoResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Todo 리포지토리 구현 클래스.
 * 데이터베이스에 대한 Todo 엔티티의 CRUD 작업을 처리합니다.
 */
@Repository
public class TodoRepositoryImpl implements TodoRepository {
    private final JdbcTemplate jdbcTemplate;

    /**
     * TodoRepositoryImpl 생성자.
     *
     * @param dataSource 데이터 소스
     */
    public TodoRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Todo 항목을 저장합니다.
     *
     * @param todo 저장할 Todo 객체
     * @return 저장된 Todo의 응답 데이터
     */
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

    /**
     * ID로 Todo 항목을 조회합니다.
     *
     * @param id 조회할 Todo의 식별자
     * @return 해당 ID의 Todo 객체
     * @exception ResponseStatusException 해당 ID의 Todo가 존재하지 않을 경우 404 Not Found
     */
    @Override
    public Todo findTodoById(Long id) {
        List<Todo> result = jdbcTemplate.query("select * from todo where id =?", todoRowMapper(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id 가 없습니다" + id));
    }

    /**
     * 조건에 맞는 Todo 항목들을 조회합니다.
     *
     * @param date 수정일 (선택 사항)
     * @param name 작성자 이름 (선택 사항)
     * @return 조건에 맞는 Todo 목록
     */
    @Override
    public List<TodoResponseDto> findConditionTodo(Date date, String name) {
        List<Todo> result = jdbcTemplate.query("SELECT * FROM todo WHERE editDate = ? OR name = ? ORDER BY editDate DESC",
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

    /**
     * Todo 항목을 수정합니다.
     *
     * @param id 수정할 Todo의 식별자
     * @param todo 수정할 할 일 내용 (null인 경우 이름만 수정)
     * @param name 수정할 작성자 이름
     * @return 수정된 Todo의 개수
     */
    @Override
    public int updateTodo(Long id, String todo, String name) {
        if (todo == null) {
            return jdbcTemplate.update("update todo set  name= ? where id = ?", name, id);
        } else {
            return jdbcTemplate.update("update todo set  todo =? where id = ?", todo, id);
        }
    }

    /**
     * ResultSet을 Todo 객체로 매핑하는 RowMapper.
     *
     * @return Todo 객체를 생성하는 RowMapper
     */
    private RowMapper<Todo> todoRowMapper() {
        return new RowMapper<Todo>() {
            @Override
            public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Todo(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getDate("uploadDate"),
                        rs.getDate("editDate")
                );
            }
        };
    }

    /**
     * Todo 항목을 삭제합니다.
     *
     * @param id 삭제할 Todo의 식별자
     * @exception ResponseStatusException 해당 ID의 Todo가 존재하지 않을 경우 404 Not Found
     */
    @Override
    public void deleteTodo(Long id) {
        int deleteTodo = jdbcTemplate.update("DELETE FROM todo WHERE id = ?", id);
        if (deleteTodo == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제할 id 가 존재하지 않습니다" + id);
        }
    }
}
