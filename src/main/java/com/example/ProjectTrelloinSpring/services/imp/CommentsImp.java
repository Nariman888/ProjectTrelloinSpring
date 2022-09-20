package com.example.ProjectTrelloinSpring.services.imp;

import com.example.ProjectTrelloinSpring.model.Comments;
import com.example.ProjectTrelloinSpring.model.Tasks;
import com.example.ProjectTrelloinSpring.repositories.CommentsRepository;
import com.example.ProjectTrelloinSpring.services.CommentsService;
import com.example.ProjectTrelloinSpring.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentsImp implements CommentsService {
    @Autowired
    TasksService tasksService;
    @Autowired
    CommentsRepository commentsRepository;
    @Override
    public Comments addComment(Long taskId, Comments comment) {
        Tasks t =tasksService.getTask(taskId);
        if(t!=null){
            comment.setTask(t);
            return commentsRepository.save(comment);
        }
        return null;

        }

    @Override
    public List<Comments> getComments(Long id) {
        return commentsRepository.findAllByTaskId(id);
    }
}
