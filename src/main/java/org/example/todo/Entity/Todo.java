package org.example.todo.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Todo {
    private Long id;
    private String todo;
    private String name;
    private String password;
    private Date uploadDate;
    private Date editDate;

    public Todo(String todo, String name, String password, Date uploadDate, Date editDate) {
        this.todo = todo;
        this.name = name;
        this.password = password;
        this.uploadDate = uploadDate;
        this.editDate = editDate;
    }

    public Todo(Long id, String todo, String name, Date uploadDate, Date editDate) {
        this.id = id;
        this.todo = todo;
        this.name = name;
        this.uploadDate = uploadDate;
        this.editDate = editDate;
    }
}

