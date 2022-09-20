package com.example.ProjectTrelloinSpring.repositories;

import com.example.ProjectTrelloinSpring.model.TaskCategories;
import com.example.ProjectTrelloinSpring.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TasksRepository extends JpaRepository<Tasks,Long> {
    List<Tasks> findAllByFolderId(Long folderId);

    @Modifying
    @Query("DELETE FROM Tasks t WHERE t.folder.id=:folderId")
    void deleteTasksByFolderId(@Param("folderId") Long folderId);
}
