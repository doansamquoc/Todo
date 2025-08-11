package com.learn.Todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.Todo.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
