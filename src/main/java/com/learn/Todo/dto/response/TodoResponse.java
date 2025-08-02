package com.learn.Todo.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.learn.Todo.type.TodoStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodoResponse {
    Long id;
    String text;
    LocalDateTime startTime;
    LocalDateTime endTime;
    TodoStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
