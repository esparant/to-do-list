package com.tak.todolist.domain.repository;

import com.tak.todolist.domain.entity.ToDoList;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ToDoListRepository {

    private final EntityManager em;

    public void save(ToDoList toDoList) {
        em.persist(toDoList);
    }

    public List<ToDoList> findAll() {
        return em.createQuery("select t from ToDoList t", ToDoList.class)
                .getResultList();
    }

    public ToDoList findTodo(Long id) {
        return em.find(ToDoList.class, id);
    }

    public ToDoList update(ToDoList update) {
        ToDoList todo = em.find(ToDoList.class, update.getId());
        return todo.ChangeToDo(update);
    }

    public void delete(Long id) {
        ToDoList toDoList = em.find(ToDoList.class, id);
        em.remove(toDoList);
    }

    public ToDoList complete(Long id) {
        ToDoList toDoList = em.find(ToDoList.class, id);
        return toDoList.changeStatus(toDoList.getStatus());
    }
}
