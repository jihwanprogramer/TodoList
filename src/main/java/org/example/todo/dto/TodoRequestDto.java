package org.example.todo.dto;

import lombok.Getter;

import java.sql.Date;

@Getter
public class TodoRequestDto {
    private String todo;
    private String name;
    private String password;
}
