package com.learn.Todo.dto.request;

import java.time.LocalDateTime;

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
public class TodoUpdateStatusRequest {
    TodoStatus status;
    LocalDateTime completedAt;
}
