package com.example.ProjectTrelloinSpring.services.imp;

import com.example.ProjectTrelloinSpring.model.Folders;
import com.example.ProjectTrelloinSpring.model.TaskCategories;
import com.example.ProjectTrelloinSpring.repositories.FoldersRepository;
import com.example.ProjectTrelloinSpring.repositories.TaskCategoriesRepository;
import com.example.ProjectTrelloinSpring.repositories.TasksRepository;
import com.example.ProjectTrelloinSpring.services.FoldersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoldersImp implements FoldersService {

    @Autowired
    FoldersRepository foldersRepository;
    @Autowired
    TasksRepository tasksRepository;
    @Autowired
    TaskCategoriesRepository taskCategoriesRepository;

    @Override
    public Folders getFolder(Long id) {
        if (id!=null){
            Folders folder = foldersRepository.findById(id).orElseThrow();
            return folder;
        }
        return null;
    }

    @Override
    public List<Folders> getAllFolders() {
        return foldersRepository.findAll();
    }

    @Override
    public void deleteFolder(Folders folder) {
        if (folder!=null) {
            foldersRepository.delete(folder);
            tasksRepository.deleteTasksByFolderId(folder.getId());
        }
    }

    @Override
    public Folders addFolder(Folders folder) {
        if (folder!=null){
            return foldersRepository.save(folder);

        }
        return null;
    }

    @Override
    public Folders deleteCategoryFromFolder(Long folderId, Long categoryId) {
        if (folderId!=null && categoryId!=null) {
            Folders folder=foldersRepository.findById(folderId).orElseThrow();
            List<TaskCategories> taskCategories = folder.getCategories();
            if (taskCategories!=null){
                TaskCategories category = taskCategoriesRepository.findById(categoryId).orElseThrow();
                taskCategories.remove(category);
                folder.setCategories(taskCategories);
                foldersRepository.save(folder);
            }

        }
        return null;
    }


    @Override
    public Folders addCategoryToFolder(Long folderId, Long categoryId) {
        Folders folder = foldersRepository.findById(folderId).orElseThrow();
        TaskCategories category = taskCategoriesRepository.findById(categoryId).orElseThrow();
        if (folder!=null && category!=null ) {
            List<TaskCategories> categoriesList = folder.getCategories();
            if (categoriesList==null ) {
                categoriesList = new ArrayList<>();
            }
            categoriesList.add(category);
            folder.setCategories(categoriesList);
            return foldersRepository.save(folder);
        }
        return null;
    }

    @Override
    public Folders addNewCategoryToFolder(Folders folder, TaskCategories category) {
        return null;
    }
}
