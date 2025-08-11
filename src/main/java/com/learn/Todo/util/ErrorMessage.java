package com.learn.Todo.util;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorMessage {
    TODO_NOT_FOUND(HttpStatus.NOT_FOUND, "Not found a TODO for this ID."),
    CANNOT_DELETE_TODO(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot delete this TODO."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found.");

    HttpStatus status;
    String message;
}
