package com.tak.todolist.domain.controller;

import com.tak.todolist.domain.entity.ToDoList;
import com.tak.todolist.domain.entity.dto.ToDoListDto;
import com.tak.todolist.domain.service.ToDoListService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ToDoListController {

    private final ToDoListService service;

    @GetMapping("/todolist")
    public String getTodolist(Model model) {

        model.addAttribute("todo", new ToDoListDto());
        model.addAttribute("todos", getToDoListDtos());

        return "/todolist";
    }

    @PostMapping("/todolist")
    public String postToDoList(@Validated @ModelAttribute("todo") ToDoListDto toDoListDto, BindingResult bindingResult,
                               Model model) {
        model.addAttribute("todos", getToDoListDtos());
        if (bindingResult.hasErrors()) {
            log.info("errors : {}", bindingResult);
            model.addAttribute("todo", toDoListDto);
            return "/todolist";
        }

        service.saveToDoList(new ToDoList(toDoListDto));

        return "redirect:/todolist";
    }

    @GetMapping("/todolist/{id}")
    public ResponseEntity<ToDoListDto> getToDoList(@PathVariable("id") Long id) {

        ToDoList todo = service.findById(id);

        return new ResponseEntity<>(new ToDoListDto(todo), HttpStatus.OK);
    }

    @PostMapping("/todolist/update")
    public ResponseEntity<?> update(@Valid @RequestBody ToDoListDto update, BindingResult bindingResult) {
        log.info("Received update request: {}", update);

        if (bindingResult.hasErrors()) {
            log.info("Validation errors: {}", bindingResult);
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        ToDoList updatedToDoList = service.updateToDoList(new ToDoList(update));
        return new ResponseEntity<>(new ToDoListDto(updatedToDoList), HttpStatus.OK);
    }

    @DeleteMapping("/todolist/delete/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        service.deleteToDoList(id);
        return HttpStatus.OK;
    }

    @PostMapping("/todolist/complete/{id}")
    public ResponseEntity<ToDoListDto> completeTodo(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new ToDoListDto(service.completeToDo(id)), HttpStatus.OK);
    }

    private List<ToDoListDto> getToDoListDtos() {
        return service.findAllToDoLists().stream().map(ToDoListDto::new).toList();
    }
}

