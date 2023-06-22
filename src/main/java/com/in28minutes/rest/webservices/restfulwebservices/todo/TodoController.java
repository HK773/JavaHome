package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class TodoController {
	
	private TodoService todoService;
	private Logger logger =  LoggerFactory.getLogger(getClass());
	
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@GetMapping("/users/{username}/todos")
	public List<Todo> retrieveAllTodos(@PathVariable String username) {
		return todoService.findByUsername(username);
	}
	
	@GetMapping("/users/{username}/todos/{id}")
	public Todo retrieveTodoById(@PathVariable String username, 
			@PathVariable int id) {
		return todoService.findById(id);
	}
	
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Todo> deleteTodoById(@PathVariable String username, 
			@PathVariable int id) {
		todoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/users/{username}/todos/{id}")
	public Todo updateTodoById(@PathVariable String username, 
			@PathVariable int id, @RequestBody Todo todo ) {
		todoService.updateTodo(todo);
		return todo;
	}
	
	@PostMapping("/users/{username}/todos")
	public Todo createTodoById(@PathVariable String username,
			@RequestBody Todo todo ) {
		Todo createdTodo = todoService.addTodo(username, todo.getDescription(), 
				todo.getTargetDate(), todo.isDone());
		logger.debug(createdTodo.toString());
		return createdTodo;
	}
	
	
	
}
