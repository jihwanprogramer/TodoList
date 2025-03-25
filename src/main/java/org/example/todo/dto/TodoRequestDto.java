package org.example.todo.dto;

import lombok.Getter;

/**
 * Todo 요청 Dto
 */
@Getter
public class TodoRequestDto {
    /**
     * 할 일
     */
    private String todo;
    /**
     * 이름
     */
    private String name;
    /**
     * 비밀번호
     */
    private String password;
}
