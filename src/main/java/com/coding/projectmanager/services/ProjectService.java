package com.coding.projectmanager.services;

import com.coding.projectmanager.models.Project;
import com.coding.projectmanager.models.User;
import com.coding.projectmanager.repositories.ProjectRepository;
import com.coding.projectmanager.repositories.UserRepository;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    public List<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public Project findOneProject(Long id) {
        Optional<Project> oneProject = projectRepository.findById(id);
        if(oneProject.isPresent()){
            return oneProject.get();
        }else{
            return null;
        }
    }

    public Project findProjectById(Long id){
        return projectRepository.findProjectById(id);
    }

    public List<Project> findProjectByUserId(Long userId){
        return projectRepository.findAllByUser_Id(userId);
    }

    public List<Project> findAllAddedProjects(User user){
        return projectRepository.findAllByUsers(user);
    }

    public List<Project> findAllMissingProjects(User user){
        return projectRepository.findByUserNotAndUsersNotContains(user,user);
    }

    public Project addOrUpdateProject(Project project, Long id) {
        project.setTitle(WordUtils.capitalizeFully(project.getTitle()));
        project.setUser(userService.findUser(id));
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public Project addUserToTeam(Long projectId, Long userId) {
        Project oneProject = projectRepository.findProjectById(projectId);
        User oneuser = userRepository.findUserById(userId);
        oneProject.getUsers().add(oneuser);
        return projectRepository.save(oneProject);
    }

    public Project removeUserFromTeam(Long projectId, Long userId) {
        Project oneProject = projectRepository.findProjectById(projectId);
        User oneUser = userRepository.findUserById(userId);
        oneProject.getUsers().remove(oneUser);
        return projectRepository.save(oneProject);
    }
}
