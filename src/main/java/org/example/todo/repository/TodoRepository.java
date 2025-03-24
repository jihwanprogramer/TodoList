package org.example.todo.repository;

import org.example.todo.Entity.Todo;
import org.example.todo.dto.TodoResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    TodoResponseDto saveTodo(Todo todo);

    Todo findTodoById(Long id);

    List<TodoResponseDto> findConditionTodo(Date date, String name);

    int updateTodo(Long id, String Todo, String name);
}
