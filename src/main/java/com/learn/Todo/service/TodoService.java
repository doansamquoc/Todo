package com.learn.Todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.Todo.dto.request.TodoCreationRequest;
import com.learn.Todo.dto.response.TodoResponse;
import com.learn.Todo.entity.Todo;
import com.learn.Todo.mapper.TodoMapper;
import com.learn.Todo.repository.TodoRepository;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    TodoMapper todoMapper;

    public TodoResponse createTodo(TodoCreationRequest request) {
        Todo todo = todoMapper.toTodoCreationRequest(request);

        return todoMapper.toTodoResponse(todoRepository.save(todo));
    }
}
