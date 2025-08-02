package com.learn.Todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.Todo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
