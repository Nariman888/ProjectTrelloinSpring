package com.example.ProjectTrelloinSpring.controllers;

import com.example.ProjectTrelloinSpring.model.Comments;
import com.example.ProjectTrelloinSpring.model.Folders;
import com.example.ProjectTrelloinSpring.model.TaskCategories;
import com.example.ProjectTrelloinSpring.model.Tasks;
import com.example.ProjectTrelloinSpring.services.CommentsService;
import com.example.ProjectTrelloinSpring.services.FoldersService;
import com.example.ProjectTrelloinSpring.services.TaskCategoriesService;
import com.example.ProjectTrelloinSpring.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.PushBuilder;
import java.util.List;

@Controller
public class HomeTrelloController {

    @Autowired
    TasksService tasksService;

    @Autowired
    FoldersService foldersService;

    @Autowired
    TaskCategoriesService categoriesService;

    @Autowired
    CommentsService commentsService;

    @GetMapping(value = "/")
    public String homepage(Model model) {
        List<Folders> folders = foldersService.getAllFolders();
        model.addAttribute("folders", folders);
        return "home";
    }

    @PostMapping(value = "/addFolder")
    public String addFolder(Folders folder) {
        foldersService.addFolder(folder);
        return "redirect:/";
    }
    @PostMapping(value = "/deleteFolder")
    public String deleteFolder(Folders folder){
        foldersService.deleteFolder(folder);
        return "redirect:/";
    }
    @GetMapping(value = "/folder/{id}")
    public String detailsOfFolder(Model model,@PathVariable(name = "id") Long id){
        Folders folder = foldersService.getFolder(id);
        model.addAttribute("folder", folder);

        List<Tasks> tasks = tasksService.getAllTasksFromFolder(id);
        model.addAttribute("tasks",tasks);

        List<TaskCategories> categoriesFromFolder = categoriesService.getCategoriesFromFolder(id);
        model.addAttribute("categoriesOfFolder",categoriesFromFolder);

        List<TaskCategories> allCategories = categoriesService.getAllTaskCategories();
        model.addAttribute("allCategories", allCategories);
        return "folderDetails";
    }

    @PostMapping(value = "/assignCategory")
    public String assignCategory(@RequestParam(name = "fold_id") Long idf,
                                 @RequestParam(name = "cat_id") Long idc) {
        foldersService.addCategoryToFolder(idf, idc);
        return "redirect:/folder/" + idf;
    }

    @PostMapping(value = "/addTaskToFolder")
    public String addTaskToFolder(@RequestParam(name = "description") String description,
                                  @RequestParam(name = "title") String title,
                                  @RequestParam(name = "fold_id") Long id){
        int status=0;
        Folders folder=foldersService.getFolder(id);
        if (folder!=null) {
            Tasks task = tasksService.addTask(new Tasks(null,title,description,status,folder));
            tasksService.addTask(task);
        }
        return "redirect:/folder/" + id;
    }

    @PostMapping(value = "/deleteCategory")
    public String deleteCategoryFromFolder(@RequestParam(name = "fold_id") Long id,
                                           @RequestParam(name = "cat_id") Long idc) {
        foldersService.deleteCategoryFromFolder(id,idc);
        return "redirect:/folder/" + id;
    }

    @GetMapping(value = "/detailsOfTask/{id}")
    public String detailsOfTask(@PathVariable(name = "id") Long id,Model model){
        Tasks task = tasksService.getTask(id);
        List<Comments> commentaries = commentsService.getComments(id);
        if (!commentaries.isEmpty()){
            model.addAttribute("comments", commentaries);
        }
        model.addAttribute("task", task);
        return"TaskDetails";
    }

    @PostMapping("/renewTaskInfo")
    public String renewTaskInfo(@RequestParam(name = "id")Long id,
                                @RequestParam(name = "status") int status){
        Tasks task = tasksService.changeStatusOfTheTask(id, status);
        return "redirect:/folder/"+task.getFolder().getId();
    }

    @PostMapping("/deleteTASK")
    public String deleteTask(@RequestParam(name = "taskId") Long id) {
        Tasks task = tasksService.getTask(id);
        tasksService.deleteTask(id);
        return "redirect:/folder/" + task.getFolder().getId();
    }

    @PostMapping("/addComment")
    public String addComment(@RequestParam(name = "taskId") Long Id,
                             Comments comment){
        commentsService.addComment(Id,comment);
        return "redirect:/detailsOfTask/" + Id;
    }
}
