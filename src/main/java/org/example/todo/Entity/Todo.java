package org.example.todo.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Date;

@Getter
@AllArgsConstructor
public class Todo {
    private String todo;
    private String name;
    private String password;
    private Date uploadDate;
    private Date editDate;
    private Long id;

    public Todo(String todo,String name,String password,Date uploadDate){
        this.todo = todo;
        this.name = name;
        this.password = password;
        this.uploadDate = uploadDate;
    }
}

