package com.learn.Todo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.learn.Todo.dto.request.TodoCreationRequest;
import com.learn.Todo.dto.response.TodoResponse;
import com.learn.Todo.entity.Todo;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TodoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Todo toTodoCreationRequest(TodoCreationRequest request);

    TodoResponse toTodoResponse(Todo todo);
}
