package com.example.ProjectTrelloinSpring.services.imp;

import com.example.ProjectTrelloinSpring.model.Folders;
import com.example.ProjectTrelloinSpring.model.TaskCategories;
import com.example.ProjectTrelloinSpring.repositories.TaskCategoriesRepository;
import com.example.ProjectTrelloinSpring.services.FoldersService;
import com.example.ProjectTrelloinSpring.services.TaskCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskCategoriesImp implements TaskCategoriesService {

    @Autowired
    FoldersService foldersService;

    @Autowired
    TaskCategoriesRepository categoriesRepository;

    @Override
    public TaskCategories addTaskCategory(TaskCategories taskCategory) {

        return categoriesRepository.save(taskCategory);
    }

    @Override
    public void deleteTaskCategory(TaskCategories taskCategory) {

    }

    @Override
    public List<TaskCategories> getAllTaskCategories() {

        return categoriesRepository.findAll() ;
    }

    @Override
    public TaskCategories getTaskCategory(Long id) {
        return categoriesRepository.findById(id).orElseThrow();
    }

    @Override
    public List<TaskCategories> getCategoriesFromFolder(Long id) {
        Folders folder = foldersService.getFolder(id);
        List<TaskCategories> taskCategories = new ArrayList<>();
        if (folder!=null) {
            taskCategories = folder.getCategories();
        }
        return taskCategories;
    }
}
