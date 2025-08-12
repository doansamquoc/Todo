package com.learn.Todo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.learn.Todo.dto.response.BaseResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

        @PostMapping("/logout")
        public ResponseEntity<BaseResponse<?>> logout(HttpServletRequest httpServletRequest,
                        HttpServletResponse httpServletResponse) throws ServletException {
                httpServletRequest.logout();
                httpServletRequest.getSession().invalidate();

                return ResponseEntity.status(HttpStatus.OK).body(
                                BaseResponse.builder()
                                                .success(true)
                                                .statusCode(HttpStatus.OK.value())
                                                .message("Logged out successfully.")
                                                .build());
        }

}
