package com.namanlakhani.casestudy.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.namanlakhani.casestudy.taskmanager.entity.Task;
import com.namanlakhani.casestudy.taskmanager.service.TaskManagerService;

@RestController
public class TaskManagerController {

	@Autowired
	private TaskManagerService taskManagerService;

	public void setTaskManagerService(TaskManagerService taskManagerService) {
		this.taskManagerService = taskManagerService;
	}
	
	@GetMapping("/home")
	public String home() {
		return "forward:/index.html";
	}

	@GetMapping("/api/tasks")
	public List<Task> getAllTasks() {
		List<Task> tasks = taskManagerService.getAllTasks();
		return tasks;
	}

	@GetMapping("/api/tasks/{taskId}")
	public Task getTask(@PathVariable(name = "taskId") Long taskId) {
		Task task = taskManagerService.getTask(taskId);
		return task;
	}

	@PostMapping(path = "/api/tasks", consumes = "application/json", produces = "application/json")
	public boolean saveTask(@RequestBody Task task) {
		try {
			taskManagerService.updateTask(task);
		} catch (Exception e) {
			System.out.println("Save Task Failed : " + e.getMessage());
			return false;
		}
		return true;
	}

	@PutMapping("/api/tasks/{taskId}")
	public boolean updateTask(@RequestBody Task task, @PathVariable(name = "taskId") Long taskId) {
		try {
			Task taskRetrived = taskManagerService.getTask(taskId);
			if (taskRetrived != null) {
				taskManagerService.updateTask(task);
			} else {
				System.out.println("updateTask: No task available in the task id : " + taskId);
				return false;
			}
		} catch (Exception e) {
			System.out.println("Update Task Failed : " + e.getMessage());
			return false;
		}
		return true;
	}

	@DeleteMapping("/api/tasks/{taskId}")
	public boolean deleteTask(@PathVariable(name = "taskId") Long taskId) {
		try {
			Task taskRetrived = taskManagerService.getTask(taskId);
			if (taskRetrived != null) {
				taskRetrived.setTaskStatus("I");
				taskManagerService.updateTask(taskRetrived);
			} else {
				System.out.println("deleteTask: No task available in the task id : " + taskId);
				return false;
			}
		} catch (Exception e) {
			System.out.println("Delete Task Failed : " + e.getMessage());
			return false;
		}
		return true;
	}

}
