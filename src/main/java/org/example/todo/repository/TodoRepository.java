package org.example.todo.repository;

import org.example.todo.Entity.Todo;
import org.example.todo.dto.TodoResponseDto;

import java.util.Date;
import java.util.List;

/**
 * Todo 리포지토리 인터페이스.
 * Todo 엔티티에 대한 데이터 접근 메서드를 정의
 */
public interface TodoRepository {

    /**
     * Todo 항목을 저장합니다.
     *
     * @param todo 저장할 Todo 객체
     * @return 저장된 Todo의 응답 데이터
     */
    TodoResponseDto saveTodo(Todo todo);

    /**
     * ID로 Todo 항목을 조회합니다.
     *
     * @param id 조회할 Todo의 식별자
     * @return 해당 ID의 Todo 객체
     */
    Todo findTodoById(Long id);

    /**
     * 조건에 맞는 Todo 항목들을 조회합니다.
     *
     * @param date 수정일 (선택 사항)
     * @param name 작성자 이름 (선택 사항)
     * @return 조건에 맞는 Todo 목록
     */
    List<TodoResponseDto> findConditionTodo(Date date, String name);

    /**
     * Todo 항목을 수정합니다.
     *
     * @param id 수정할 Todo의 식별자
     * @param Todo 수정할 할 일 내용
     * @param name 수정할 작성자 이름
     * @return 수정된 Todo의 개수
     */
    int updateTodo(Long id, String Todo, String name);

    /**
     * Todo 항목을 삭제합니다.
     *
     * @param id 삭제할 Todo의 식별자
     */
    void deleteTodo(Long id);
}
