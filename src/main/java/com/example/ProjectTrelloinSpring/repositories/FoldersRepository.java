package com.example.ProjectTrelloinSpring.repositories;

import com.example.ProjectTrelloinSpring.model.Folders;
import com.example.ProjectTrelloinSpring.model.TaskCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface FoldersRepository extends JpaRepository<Folders,Long> {

}
