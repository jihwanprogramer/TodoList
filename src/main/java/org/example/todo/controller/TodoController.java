package org.example.todo.controller;

import org.example.todo.dto.TodoRequestDto;
import org.example.todo.dto.TodoResponseDto;
import org.example.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody TodoRequestDto dto) {

        return new ResponseEntity<>(todoService.saveTodo(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> findTodoId(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.findTodoId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> findConditionTodo(
            @RequestParam(required = false) Date editDate,
            @RequestParam(required = false) String name) {
        return new ResponseEntity<>(todoService.findConditionTodo(editDate, name), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(
            @PathVariable long id,
            @RequestBody TodoRequestDto requestDto
    ) {
        return new ResponseEntity<>(todoService.updateTodo(id, requestDto.getTodo(), requestDto.getName()), HttpStatus.OK);
    }


}
