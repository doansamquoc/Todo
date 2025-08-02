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
    TODO_NOT_FOUND(HttpStatus.NOT_FOUND, "Not found a todo for this ID.");

    HttpStatus status;
    String message;
}
