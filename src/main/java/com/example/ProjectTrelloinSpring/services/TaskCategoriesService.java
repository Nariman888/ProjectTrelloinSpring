package com.example.ProjectTrelloinSpring.services;

import com.example.ProjectTrelloinSpring.model.TaskCategories;

import java.util.List;

public interface TaskCategoriesService {
   TaskCategories addTaskCategory(TaskCategories taskCategory);
   void deleteTaskCategory(TaskCategories taskCategory);
   List <TaskCategories> getAllTaskCategories();
   TaskCategories getTaskCategory(Long id);

   List<TaskCategories> getCategoriesFromFolder(Long id);
}
