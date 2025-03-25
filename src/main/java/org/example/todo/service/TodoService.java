package org.example.todo.service;

import org.example.todo.dto.TodoRequestDto;
import org.example.todo.dto.TodoResponseDto;

import java.util.Date;
import java.util.List;

/**
 * Todo 서비스 인터페이스.
 * Todo 항목에 대한 비즈니스 로직을 정의합니다.
 */
public interface TodoService {
    /**
     * Todo 항목을 저장합니다.
     *
     * @param requestDto Todo 요청 데이터
     * @return {@link TodoResponseDto} 생성된 Todo의 응답 데이터
     */
    TodoResponseDto saveTodo(TodoRequestDto requestDto);

    /**
     * ID로 Todo 항목을 조회합니다.
     *
     * @param id 조회할 Todo의 식별자
     * @return {@link TodoResponseDto} 조회된 Todo의 응답 데이터
     */
    TodoResponseDto findTodoId(Long id);

    /**
     * 조건에 맞는 Todo 항목들을 조회합니다.
     *
     * @param editDate 수정일 (선택 사항)
     * @param name Todo 항목의 이름 (선택 사항)
     * @return {@link List<TodoResponseDto>} 조건에 맞는 Todo 목록
     */
    List<TodoResponseDto> findConditionTodo(Date editDate, String name);

    /**
     * Todo 항목을 수정합니다.
     *
     * @param id 식별자
     * @param todo 수정할 할 일 내용
     * @param name 수정할 작성자 이름
     * @param password 비밀번호
     * @return {@link TodoResponseDto} 수정된 Todo의 응답 데이터
     */
    TodoResponseDto updateTodo(Long id, String todo, String name, String password);

    /**
     * Todo 항목을 삭제합니다.
     *
     * @param id 삭제할 Todo의 식별자
     */
    void deleteTodo(Long id);
}
