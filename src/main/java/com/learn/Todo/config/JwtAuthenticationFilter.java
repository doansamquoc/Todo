package com.learn.Todo.config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.learn.Todo.entity.User;
import com.learn.Todo.exception.BaseException;
import com.learn.Todo.repository.UserRepository;
import com.learn.Todo.util.ErrorMessage;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {
        String authHeader = httpServletRequest.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                String email = jwtTokenService.getEmailFromToken(token);
                User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new BaseException(ErrorMessage.USER_NOT_FOUND));
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null,
                        List.of());
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception ex) {

            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
