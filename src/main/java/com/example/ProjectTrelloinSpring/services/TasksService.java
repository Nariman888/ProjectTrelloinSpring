package com.example.ProjectTrelloinSpring.services;

import com.example.ProjectTrelloinSpring.model.Tasks;
import org.springframework.scheduling.config.Task;

import java.util.List;

public interface TasksService {
    List<Tasks> getAllTasksFromFolder(Long id);
    Tasks getTask(Long id);
    void deleteTask(Long id);
    Tasks addTask(Tasks task);
    Tasks changeStatusOfTheTask(Long taskId,int statusId);
}
