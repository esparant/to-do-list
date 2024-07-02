package com.tak.todolist.domain.service;

import com.tak.todolist.domain.entity.ToDoList;
import com.tak.todolist.domain.repository.ToDoListRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ToDoListService {

    private final ToDoListRepository repository;

    @Transactional
    public void saveToDoList(ToDoList toDoList) {
        repository.save(toDoList);
    }

    public List<ToDoList> findAllToDoLists() {
        return repository.findAll();
    }

    public ToDoList findById(Long id) {
        return repository.findTodo(id);
    }

    @Transactional
    public ToDoList updateToDoList(ToDoList toDoList) {
        return repository.update(toDoList);
    }

    @Transactional
    public void deleteToDoList(Long id) {
        repository.delete(id);
    }

    @Transactional
    public ToDoList completeToDo(Long id) {
        return repository.complete(id);
    }
}
