package org.example.todo.service;

import org.example.todo.Entity.Todo;
import org.example.todo.dto.TodoRequestDto;
import org.example.todo.dto.TodoResponseDto;
import org.example.todo.repository.TodoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    @Override
    public TodoResponseDto saveTodo(TodoRequestDto requestDto) {
        Date currentDate = new Date();
        Date editDate = new Date();
        Todo todo = new Todo(requestDto.getTodo(), requestDto.getName(), requestDto.getPassword(),currentDate,editDate);
        return todoRepository.saveTodo(todo);
    }

    @Override
    public TodoResponseDto findTodoId(Long id) {
        Todo todo = todoRepository.findTodoById(id);
        return new TodoResponseDto(todo);
    }

    @Override
    public List<TodoResponseDto> findConditionTodo(Date editDate, String name) {
        return todoRepository.findConditionTodo(editDate, name);
    }

    @Override
    public TodoResponseDto updateTodo(Long id, String todo, String name) {
        Todo todos =todoRepository.findTodoById(id);

        if (todo == null && name == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "값이 없다");
        }

        int updateRow = todoRepository.updateTodo(id,todo,name);

        if(updateRow ==0){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
            }


        return new TodoResponseDto(todos);
    }

}
