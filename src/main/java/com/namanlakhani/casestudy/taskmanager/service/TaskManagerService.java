package com.namanlakhani.casestudy.taskmanager.service;

import java.util.List;

import com.namanlakhani.casestudy.taskmanager.entity.Task;


public interface TaskManagerService {

	public Task getTask(Long taskId);
	public List<Task> getAllTasks();
	public void updateTask(Task task);
	
}
