package com.namanlakhani.casestudy.taskmanager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namanlakhani.casestudy.taskmanager.entity.Task;
import com.namanlakhani.casestudy.taskmanager.repository.TaskManagerRepository;
import com.namanlakhani.casestudy.taskmanager.service.TaskManagerService;

@Service
public class TaskManagerServiceImpl implements TaskManagerService {
	
	@Autowired
	private TaskManagerRepository taskManagerRepository;
	
	public void setTaskManagerRepository(TaskManagerRepository taskManagerRepository) {
		this.taskManagerRepository = taskManagerRepository;
	}

	@Override
	public Task getTask(Long taskId) {
		Optional<Task> optionalTask = taskManagerRepository.findById(taskId);
		return optionalTask.get();
	}

	@Override
	public List<Task> getAllTasks() {
		List<Task> tasks = taskManagerRepository.findAll();
		return tasks;
	}

	@Override
	public void updateTask(Task task) {
		taskManagerRepository.save(task);
	}

}
