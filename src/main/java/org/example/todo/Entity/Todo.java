package org.example.todo.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Todo 엔티티 클래스.
 */
@Getter
@AllArgsConstructor
public class Todo {

    /**
     * 식별자.
     */
    private Long id;

    /**
     * 할 일.
     */
    private String todo;

    /**
     * 작성자 이름.
     */
    private String name;

    /**
     * 비밀번호.
     */
    private String password;

    /**
     * 생성일.
     */
    private Date uploadDate;

    /**
     * 수정일.
     */
    @Setter
    private Date editDate;

    /**
     * Todo 객체 생성자.
     *
     * @param todo 할 일
     * @param name 작성자 이름
     * @param password 비밀번호
     * @param uploadDate 생성일
     * @param editDate 수정일
     */
    public Todo(String todo, String name, String password, Date uploadDate, Date editDate) {
        this.todo = todo;
        this.name = name;
        this.password = password;
        this.uploadDate = uploadDate;
        this.editDate = editDate;
    }
}
