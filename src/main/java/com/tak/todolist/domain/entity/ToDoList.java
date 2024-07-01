package com.tak.todolist.domain.entity;

import com.tak.todolist.domain.entity.dto.ToDoListDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class ToDoList {

    public ToDoList(ToDoListDto toDoListDto) {
        this.id = toDoListDto.getId();
        this.title = toDoListDto.getTitle();
        this.description = toDoListDto.getDescription();
        status = ToDoListStatus.CONT;
    }

    public ToDoList(String title, String description) {
        this.title = title;
        this.description = description;
        status = ToDoListStatus.CONT;
    }

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private ToDoListStatus status;

    public ToDoList ChangeToDo(ToDoList update) {
        this.title = update.title;
        this.description = update.description;
        return this;
    }

    public void changeStatus(ToDoListStatus status) {
        this.status = ToDoListStatus.END;
    }

    public enum ToDoListStatus {
        CONT("진행중"), END("완료");

        public final String status;

        ToDoListStatus(String status) {
            this.status = status;
        }

    }

}
