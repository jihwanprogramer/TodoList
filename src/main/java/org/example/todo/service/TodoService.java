package org.example.todo.service;

import org.example.todo.Entity.Todo;
import org.example.todo.dto.TodoRequestDto;
import org.example.todo.dto.TodoResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface TodoService {
    TodoResponseDto saveTodo(TodoRequestDto requestDto);

    TodoResponseDto findTodoId(Long id);

    List<TodoResponseDto> findConditionTodo(Date editDate, String name);

    TodoResponseDto updateTodo(Long id, String todo, String name);
}
