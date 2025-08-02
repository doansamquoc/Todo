package com.learn.Todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.Todo.dto.request.TodoCreationRequest;
import com.learn.Todo.dto.request.TodoUpdateRequest;
import com.learn.Todo.dto.response.BaseResponse;
import com.learn.Todo.dto.response.TodoResponse;
import com.learn.Todo.service.TodoService;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    TodoService todoService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<TodoResponse>> create(@RequestBody TodoCreationRequest request) {
        TodoResponse todoResponse = todoService.createTodo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                BaseResponse.<TodoResponse>builder()
                        .success(true)
                        .statusCode(HttpStatus.CREATED.value())
                        .message("TODO has been successfully created.")
                        .data(todoResponse)
                        .build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse<TodoResponse>> updateTodo(@PathVariable Long id,
            @RequestBody TodoUpdateRequest request) {
        TodoResponse todoResponse = todoService.updateTodoById(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(
                BaseResponse.<TodoResponse>builder()
                        .success(true)
                        .statusCode(HttpStatus.OK.value())
                        .message("TODO has been successfully updated.")
                        .data(todoResponse)
                        .build());
    }
}
