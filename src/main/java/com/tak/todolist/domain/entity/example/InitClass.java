package com.tak.todolist.domain.entity.example;

import com.tak.todolist.domain.entity.ToDoList;
import com.tak.todolist.domain.repository.ToDoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
@Transactional
public class InitClass {

    private final ToDoListRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        repository.save(new ToDoList("test1", "test test"));
        repository.save(new ToDoList("test2", "test test"));
        repository.save(new ToDoList("test3", "test test"));
        repository.save(new ToDoList("test4", "test test"));
        repository.save(new ToDoList("test5", "test test"));
        repository.save(new ToDoList("test6", "test test"));
        repository.save(new ToDoList("test7", "test test"));
        repository.save(new ToDoList("test8", "test test"));
        repository.save(new ToDoList("test9", "test test"));

    }

}


