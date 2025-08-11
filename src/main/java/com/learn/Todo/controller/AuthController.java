package com.learn.Todo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.learn.Todo.dto.response.BaseResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @GetMapping("/check")
    public ResponseEntity<BaseResponse<?>> checkAuth(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(BaseResponse.builder()
                            .success(false)
                            .statusCode(HttpStatus.UNAUTHORIZED.value())
                            .message("Unauthorized")
                            .build());
        }

        return ResponseEntity.ok(
                BaseResponse.builder()
                        .success(true)
                        .statusCode(HttpStatus.OK.value())
                        .message("Authenticated.")
                        .build());
    }

}
