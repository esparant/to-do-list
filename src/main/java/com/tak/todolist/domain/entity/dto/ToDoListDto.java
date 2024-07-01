package com.tak.todolist.domain.entity.dto;

import com.tak.todolist.domain.entity.ToDoList;
import com.tak.todolist.domain.entity.ToDoList.ToDoListStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Data
public class ToDoListDto {

    private Long id;

    @NotBlank(message = "비어서는 안돼 임마")
    @Length(max = 10, message = "메시지가 너무 깁니다.")
    private String title;

    private String description;

    private ToDoListStatus status;

    public ToDoListDto(ToDoList toDoList) {
        this.id = toDoList.getId();
        this.title = toDoList.getTitle();
        this.description = toDoList.getDescription();
        this.status = toDoList.getStatus();
    }
}
