package org.example.todo.service;

import org.example.todo.Entity.Todo;
import org.example.todo.dto.TodoRequestDto;
import org.example.todo.dto.TodoResponseDto;

public interface TodoService {
    TodoResponseDto saveTodo(TodoRequestDto requestDto);
}
