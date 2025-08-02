package com.learn.Todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.Todo.dto.request.TodoCreationRequest;
import com.learn.Todo.dto.request.TodoUpdateRequest;
import com.learn.Todo.dto.response.TodoResponse;
import com.learn.Todo.entity.Todo;
import com.learn.Todo.exception.BaseException;
import com.learn.Todo.mapper.TodoMapper;
import com.learn.Todo.repository.TodoRepository;
import com.learn.Todo.util.ErrorMessage;

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

    public TodoResponse updateTodoById(Long id, TodoUpdateRequest request) {
        Todo todo = findById(id);
        todo = todoMapper.toTodoUpdateRequest(request, todo);
        return todoMapper.toTodoResponse(todoRepository.save(todo));
    }

    public Todo findById(Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new BaseException(ErrorMessage.TODO_NOT_FOUND));
    }
}
