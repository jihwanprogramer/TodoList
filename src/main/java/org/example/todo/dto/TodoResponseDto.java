package org.example.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.todo.Entity.Todo;

import java.util.Date;


@Getter
@AllArgsConstructor
public class TodoResponseDto {
    private Long id;
    private String todo;
    private String name;
    private Date uploadDate;
    private Date editDate;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.todo = todo.getTodo();
        this.name = todo.getName();
        this.uploadDate = todo.getUploadDate();
        this.editDate = todo.getEditDate();
    }
}






