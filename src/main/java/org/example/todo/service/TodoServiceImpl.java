package org.example.todo.service;

import org.example.todo.Entity.Todo;
import org.example.todo.dto.TodoRequestDto;
import org.example.todo.dto.TodoResponseDto;
import org.example.todo.repository.TodoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    /**
     * TodoServiceImpl 생성자.
     *
     * @param todoRepository Todo 리포지토리
     */
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /**
     * Todo 항목을 저장합니다.
     *
     * @param requestDto Todo 요청 데이터
     * @return {@link TodoResponseDto} 생성된 Todo의 응답 데이터
     */
    @Override
    public TodoResponseDto saveTodo(TodoRequestDto requestDto) {
        Date currentDate = new Date();
        Date editDate = new Date();
        Todo todo = new Todo(requestDto.getTodo(), requestDto.getName(), requestDto.getPassword(), currentDate, editDate);
        return todoRepository.saveTodo(todo);
    }

    /**
     * ID로 Todo 항목을 조회합니다.
     *
     * @param id 조회할 Todo의 식별자
     * @return {@link TodoResponseDto} 조회된 Todo의 응답 데이터
     */
    @Override
    public TodoResponseDto findTodoId(Long id) {
        Todo todo = todoRepository.findTodoById(id);
        return new TodoResponseDto(todo);
    }

    /**
     * 조건에 맞는 Todo 항목들을 조회합니다.
     *
     * @param editDate 수정일 (선택 사항)
     * @param name Todo 항목의 이름 (선택 사항)
     * @return {@link List<TodoResponseDto>} 조건에 맞는 Todo 목록
     */
    @Override
    public List<TodoResponseDto> findConditionTodo(Date editDate, String name) {
        return todoRepository.findConditionTodo(editDate, name);
    }

    /**
     * Todo 항목을 수정합니다.
     *
     * @param id 식별자
     * @param todo 수정할 할 일 내용
     * @param name 수정할 작성자 이름
     * @param password 비밀번호
     * @return {@link TodoResponseDto} 수정된 Todo의 응답 데이터
     * @exception ResponseStatusException 비밀번호가 일치하지 않거나 값이 없을 경우
     */
    @Override
    public TodoResponseDto updateTodo(Long id, String todo, String name, String password) {

        Todo todos = todoRepository.findTodoById(id);
        todos.setEditDate(new Date());

        if (todo == null && name == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "값이 없다");
        }

        if (todos.getPassword() == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "저장된 비밀번호가 없습니다.");
        }

        if (!todos.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호 일치 오류");
        }

        int updateRow = todoRepository.updateTodo(id, todo, name);

        if (updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        return new TodoResponseDto(todos);
    }

    /**
     * Todo 항목을 삭제합니다.
     *
     * @param id 삭제할 Todo의 식별자
     */
    @Override
    public void deleteTodo(Long id) {
        todoRepository.deleteTodo(id);
    }
}
