package org.example.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.todo.Entity.Todo;

import java.util.Date;

/**
 * Todo 응답 데이터 전송 객체 (DTO).
 */
@Getter
@AllArgsConstructor
public class TodoResponseDto {

    /**
     * 식별자.
     */
    private Long id;

    /**
     * 할 일.
     */
    private String todo;

    /**
     * 이름.
     */
    private String name;

    /**
     * 생성일.
     */
    private Date uploadDate;

    /**
     * 수정일.
     */
    private Date editDate;

    /**
     * Todo 객체를 기반으로 하는 TodoResponseDto 생성자.
     *
     * @param todo Todo 엔티티 객체
     */
    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.todo = todo.getTodo();
        this.name = todo.getName();
        this.uploadDate = todo.getUploadDate();
        this.editDate = todo.getEditDate();
    }
}
