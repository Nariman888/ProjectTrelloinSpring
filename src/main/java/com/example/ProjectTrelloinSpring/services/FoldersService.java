package com.example.ProjectTrelloinSpring.services;

import com.example.ProjectTrelloinSpring.model.Folders;
import com.example.ProjectTrelloinSpring.model.TaskCategories;

import java.util.List;

public interface FoldersService {
    Folders getFolder(Long id);
    List<Folders> getAllFolders();
    void deleteFolder(Folders folder);
    Folders addFolder(Folders folder);
    Folders deleteCategoryFromFolder(Long folderId,Long categoryId);
    Folders addCategoryToFolder(Long folderId,Long categoryId );
    Folders addNewCategoryToFolder(Folders folder,TaskCategories category);
}
