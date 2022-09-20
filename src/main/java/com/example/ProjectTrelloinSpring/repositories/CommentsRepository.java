package com.example.ProjectTrelloinSpring.repositories;

import com.example.ProjectTrelloinSpring.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CommentsRepository extends JpaRepository<Comments,Long> {
    List<Comments> findAllByTaskId(Long id);
    void deleteCommentsByTaskId(Long id);
}
