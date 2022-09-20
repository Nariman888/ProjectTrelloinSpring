package com.example.ProjectTrelloinSpring.services;

import com.example.ProjectTrelloinSpring.model.Comments;

import java.util.List;

public interface CommentsService {
    Comments addComment(Long taskId,Comments comment);
    List <Comments> getComments(Long id);
}
