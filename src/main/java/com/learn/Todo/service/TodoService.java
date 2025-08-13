package com.learn.Todo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.learn.Todo.dto.request.TodoCompletionRequest;
import com.learn.Todo.dto.request.TodoCreationRequest;
import com.learn.Todo.dto.request.TodoUpdateRequest;
import com.learn.Todo.dto.request.TodoUpdateStatusRequest;
import com.learn.Todo.dto.response.TodoResponse;
import com.learn.Todo.entity.Todo;
import com.learn.Todo.entity.User;
import com.learn.Todo.exception.BaseException;
import com.learn.Todo.mapper.TodoMapper;
import com.learn.Todo.repository.TodoRepository;
import com.learn.Todo.repository.UserRepository;
import com.learn.Todo.type.TodoStatus;
import com.learn.Todo.util.ErrorMessage;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TodoMapper todoMapper;

    public Page<TodoResponse> getTodos(int page, int size, String sortBy, String direction) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new BaseException(ErrorMessage.USER_NOT_FOUND));

        Sort sort = Sort.by("desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return todoRepository.findByUser(user, pageable).map(todoMapper::toTodoResponse);
    }

    public TodoResponse getTodoById(Long id) {
        return todoMapper.toTodoResponse(findById(id));
    }

    public TodoResponse createTodo(TodoCreationRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("> EMAIL: " + user.getEmail());
        user = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new BaseException(ErrorMessage.USER_NOT_FOUND));

        Todo todo = todoMapper.toTodoCreationRequest(request);
        todo.setUser(user);

        return todoMapper.toTodoResponse(todoRepository.save(todo));
    }

    public TodoResponse updateTodoById(Long id, TodoUpdateRequest request) {
        Todo todo = findById(id);
        todo = todoMapper.toTodoUpdateRequest(request, todo);

        return todoMapper.toTodoResponse(todoRepository.save(todo));
    }

    public TodoResponse completionTodoById(Long id) {
        Todo todo = findById(id);

        TodoCompletionRequest request = new TodoCompletionRequest(TodoStatus.COMPLETED, LocalDateTime.now());
        todo = todoMapper.toTodoCompletionRequest(request, todo);

        return todoMapper.toTodoResponse(todoRepository.save(todo));
    }

    public TodoResponse updateStatus(Long id, TodoUpdateStatusRequest request) {
        Todo todo = findById(id);

        switch (request.getStatus()) {
            case COMPLETED:
                request = new TodoUpdateStatusRequest(TodoStatus.COMPLETED, LocalDateTime.now());
                break;
            case PENDING:
                request = new TodoUpdateStatusRequest(TodoStatus.PENDING, null);
                break;
            default:
                request = new TodoUpdateStatusRequest(TodoStatus.CANCELED, null);
                break;
        }

        todo = todoMapper.toTodoUpdateStatusRequest(request, todo);

        return todoMapper.toTodoResponse(todoRepository.save(todo));
    }

    public void deleteTodoById(Long id) {
        try {
            todoRepository.deleteById(id);
        } catch (RuntimeException ex) {
            throw new BaseException(ErrorMessage.CANNOT_DELETE_TODO);
        }
    }

    public void deleteTodos() {
        try {
            todoRepository.deleteAll();
        } catch (RuntimeException ex) {
            throw new BaseException(ErrorMessage.CANNOT_DELETE_TODO);
        }
    }

    private Todo findById(Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new BaseException(ErrorMessage.TODO_NOT_FOUND));
    }
}
