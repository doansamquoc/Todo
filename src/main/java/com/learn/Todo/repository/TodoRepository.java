package com.learn.Todo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.Todo.entity.Todo;
import com.learn.Todo.entity.User;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUser(User user);

    Page<Todo> findByUser(User user, Pageable pageable);
}
