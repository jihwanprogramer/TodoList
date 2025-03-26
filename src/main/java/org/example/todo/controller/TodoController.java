package org.example.todo.controller;

import lombok.RequiredArgsConstructor;
import org.example.todo.dto.TodoRequestDto;
import org.example.todo.dto.TodoResponseDto;
import org.example.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    /**
     * 새로운 Todo 항목을 생성하는 API.
     *
     * @param dto Todo 요청 데이터
     * @return {@link ResponseEntity<TodoResponseDto>} 생성된 Todo의 응답 데이터와 함께 201 Created 상태 코드
     */
    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody TodoRequestDto dto) {
        return new ResponseEntity<>(todoService.saveTodo(dto), HttpStatus.CREATED);
    }

    /**
     * ID로 Todo 항목을 조회하는 API.
     *
     * @param id 식별자
     * @return {@link ResponseEntity<TodoResponseDto>} 조회된 Todo의 응답 데이터와 함께 200 OK 상태 코드
     * @exception ResponseStatusException 해당 ID의 Todo가 존재하지 않을 경우 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> findTodoId(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.findTodoId(id), HttpStatus.OK);
    }

    /**
     * 조건에 맞는 Todo 항목들을 조회하는 API.
     *
     * @param editDate 수정일 (선택 사항)
     * @param name Todo 항목의 이름 (선택 사항)
     * @return {@link ResponseEntity<List<TodoResponseDto>>} 조건에 맞는 Todo 목록과 함께 200 OK 상태 코드
     */
    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> findConditionTodo(
            @RequestParam(required = false) Date editDate,
            @RequestParam(required = false) String name) {
        return new ResponseEntity<>(todoService.findConditionTodo(editDate, name), HttpStatus.OK);
    }

    /**
     * Todo 항목을 수정하는 API.
     *
     * @param id 식별자
     * @param requestDto 수정할 Todo 데이터
     * @return {@link ResponseEntity<TodoResponseDto>} 수정된 Todo의 응답 데이터와 함께 200 OK 상태 코드
     * @exception ResponseStatusException 해당 ID의 Todo가 존재하지 않을 경우 404 Not Found
     */
    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(
            @PathVariable long id,
            @RequestBody TodoRequestDto requestDto) {
        return new ResponseEntity<>(todoService.updateTodo(id, requestDto.getTodo(), requestDto.getName(), requestDto.getPassword()), HttpStatus.OK);
    }

    /**
     * Todo 항목을 삭제하는 API.
     *
     * @param id 식별자
     * @return {@link ResponseEntity<Void>} 200 OK 상태 코드
     * @exception ResponseStatusException 해당 ID의 Todo가 존재하지 않을 경우 404 Not Found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
