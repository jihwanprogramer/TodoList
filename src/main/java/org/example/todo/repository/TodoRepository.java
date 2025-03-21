package org.example.todo.repository;

import org.example.todo.Entity.Todo;
import org.example.todo.dto.TodoRequestDto;
import org.example.todo.dto.TodoResponseDto;

public interface TodoRepository {
    TodoResponseDto saveTodo(Todo todo);
}
