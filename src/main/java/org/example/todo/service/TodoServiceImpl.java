package org.example.todo.service;

import org.example.todo.Entity.Todo;
import org.example.todo.dto.TodoRequestDto;
import org.example.todo.dto.TodoResponseDto;
import org.example.todo.repository.TodoRepository;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    @Override
    public TodoResponseDto saveTodo(TodoRequestDto requestDto) {
        Date currentDate = new Date();
        Todo todo = new Todo(requestDto.getTodo(), requestDto.getName(), requestDto.getPassword(),currentDate);
        return todoRepository.saveTodo(todo);
    }
}
