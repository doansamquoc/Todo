package com.learn.Todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.Todo.dto.request.TodoCreationRequest;
import com.learn.Todo.dto.request.TodoUpdateRequest;
import com.learn.Todo.dto.request.TodoUpdateStatusRequest;
import com.learn.Todo.dto.response.BaseResponse;
import com.learn.Todo.dto.response.TodoResponse;
import com.learn.Todo.service.TodoService;

@RestController
@RequestMapping("/api/todo")
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

        @PostMapping("/completion/{id}")
        public ResponseEntity<BaseResponse<TodoResponse>> completion(@PathVariable Long id) {
                TodoResponse todoResponse = todoService.completionTodoById(id);
                return ResponseEntity.status(HttpStatus.OK).body(
                                BaseResponse.<TodoResponse>builder()
                                                .statusCode(HttpStatus.OK.value())
                                                .message("TODO has been successfully completed.")
                                                .data(todoResponse)
                                                .build());
        }

        @GetMapping()
        public ResponseEntity<BaseResponse<Page<TodoResponse>>> getTodos(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size,
                        @RequestParam(defaultValue = "createdAt") String sortBy,
                        @RequestParam(defaultValue = "desc") String direction) {
                Page<TodoResponse> todoResponse = todoService.getTodos(page, size, sortBy, direction);
                return ResponseEntity.status(HttpStatus.OK).body(
                                BaseResponse.<Page<TodoResponse>>builder()
                                                .success(true)
                                                .statusCode(HttpStatus.OK.value())
                                                .message("Get TODO(s) successfully.")
                                                .data(todoResponse)
                                                .build());
        }

        @GetMapping("/{id}")
        public ResponseEntity<BaseResponse<TodoResponse>> getTodo(@PathVariable Long id) {
                TodoResponse todoResponse = todoService.getTodoById(id);
                return ResponseEntity.status(HttpStatus.OK).body(
                                BaseResponse.<TodoResponse>builder()
                                                .success(true)
                                                .statusCode(HttpStatus.OK.value())
                                                .message("Get TODO successfully.")
                                                .data(todoResponse)
                                                .build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<BaseResponse<TodoResponse>> deleteTodo(@PathVariable Long id) {
                todoService.deleteTodoById(id);
                return ResponseEntity.status(HttpStatus.OK).body(
                                BaseResponse.<TodoResponse>builder()
                                                .success(true)
                                                .statusCode(HttpStatus.OK.value())
                                                .message("TODO has been successfully deleted.")
                                                .build());
        }

        @DeleteMapping("/deleteAll")
        public ResponseEntity<BaseResponse<TodoResponse>> deleteAllTodo() {
                todoService.deleteTodos();
                return ResponseEntity.status(HttpStatus.OK).body(
                                BaseResponse.<TodoResponse>builder()
                                                .success(true)
                                                .statusCode(HttpStatus.OK.value())
                                                .message("All TODO has been successfully deleted.")
                                                .build());
        }

        @PutMapping("/status/{id}")
        public ResponseEntity<BaseResponse<TodoResponse>> updateStatus(@PathVariable Long id,
                        @RequestBody TodoUpdateStatusRequest request) {

                TodoResponse todoResponse = todoService.updateStatus(id, request);
                return ResponseEntity.status(HttpStatus.OK).body(
                                BaseResponse.<TodoResponse>builder()
                                                .success(true)
                                                .statusCode(HttpStatus.OK.value())
                                                .message("Update status successfully.")
                                                .data(todoResponse)
                                                .build());

        }
}
