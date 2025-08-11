package com.learn.Todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.Todo.dto.response.BaseResponse;
import com.learn.Todo.dto.response.UserResponse;
import com.learn.Todo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<BaseResponse<UserResponse>> me(Authentication authentication) {
        UserResponse userResponse = userService.getCurrentUser(authentication);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResponse.<UserResponse>builder()
                        .success(true)
                        .statusCode(HttpStatus.OK.value())
                        .message("Get current user successfully.")
                        .data(userResponse).build());
    }
}
