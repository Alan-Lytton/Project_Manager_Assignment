package com.coding.projectmanager.repositories;

import com.coding.projectmanager.models.Project;
import com.coding.projectmanager.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    List<Project> findAll();

    List<Project> findAllByUser_Id(Long UserId);
    List<Project> findAllByUsers(User user);

    List<Project> findByUsersNotContains(User user);

    List<Project> findByUserNotAndUsersNotContains(User user1,User user2);

    Project findProjectById(Long projectId);
}
