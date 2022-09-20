package com.example.ProjectTrelloinSpring.services.imp;

import com.example.ProjectTrelloinSpring.model.Tasks;
import com.example.ProjectTrelloinSpring.repositories.CommentsRepository;
import com.example.ProjectTrelloinSpring.repositories.TasksRepository;
import com.example.ProjectTrelloinSpring.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasksImp implements TasksService {

    @Autowired
    TasksRepository tasksRepository;
    @Autowired
    CommentsRepository commentsRepository;

    @Override
    public List<Tasks> getAllTasksFromFolder(Long id) {
        List<Tasks> tasks =tasksRepository.findAllByFolderId(id);
        return tasks;
    }

    @Override
    public Tasks getTask(Long id) {
        if (id != null) {
            Tasks tasks=tasksRepository.findById(id).orElseThrow();
            return tasks;
        }
        return null;
    }

    @Override
    public void deleteTask(Long id) {
        commentsRepository.deleteCommentsByTaskId(id);
        tasksRepository.deleteById(id);
    }

    @Override
    public Tasks addTask(Tasks task) {
        return tasksRepository.save(task);
    }

    @Override
    public Tasks changeStatusOfTheTask(Long taskId, int statusId) {
        if(taskId!=null){
            Tasks task = getTask(taskId);
            task.setStatus(statusId);
            return addTask(task);
        }
        return null;
    }
}
