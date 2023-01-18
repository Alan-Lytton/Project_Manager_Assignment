package com.coding.projectmanager.controllers;


import com.coding.projectmanager.models.Project;
import com.coding.projectmanager.models.User;
import com.coding.projectmanager.services.ProjectService;
import com.coding.projectmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectServ;

    @Autowired
    private UserService userServ;

    @GetMapping("/home")
    public String home(Model model, HttpSession session){
        if(session.getAttribute("UserId") == null){
            return "redirect:/";
        }
        Long userId = (Long) session.getAttribute("UserId");
        User user = userServ.findUser(userId);
        model.addAttribute("missingProjects", projectServ.findAllMissingProjects(user) );
        model.addAttribute("teamProjects",projectServ.findAllAddedProjects(user));
        model.addAttribute("leadProjects", projectServ.findProjectByUserId(userId));
        model.addAttribute("id", userId);
        model.addAttribute("firstName", session.getAttribute("firstName"));
        model.addAttribute("lastName", session.getAttribute("lastName"));
        return "dashboard.jsp";
    }

    @GetMapping("/projects/{id}/join")
    public String addToTeam(@PathVariable("id")Long projectId,HttpSession session){
        if(session.getAttribute("UserId") == null){
            return "redirect:/";
        }
        Long userId = (Long) session.getAttribute("UserId");
        projectServ.addUserToTeam(projectId,userId);
        return "redirect:/home";
    }

    @GetMapping("/projects/{id}/leave")
    public String removeFromTeam(@PathVariable("id")Long projectId,HttpSession session){
        if(session.getAttribute("UserId") == null){
            return "redirect:/";
        }
        Long userId = (Long) session.getAttribute("UserId");
        projectServ.removeUserFromTeam(projectId,userId);
        return "redirect:/home";
    }

    @GetMapping("/projects/new")
    public String addProject(@ModelAttribute("newProject") Project project, Model model, HttpSession session){
        if(session.getAttribute("UserId") == null){
            return "redirect:/";
        }
        model.addAttribute("sessionId", session.getAttribute("UserId"));
        return "addProject.jsp";
    }

    @PostMapping("/projects/new")
    public String processProject(@Valid @ModelAttribute("newProject") Project project, BindingResult result, HttpSession session){
        if(result.hasErrors()){
            return "addProject.jsp";
        }
        projectServ.addOrUpdateProject(project, (Long) session.getAttribute("UserId"));
        return "redirect:/home";
    }

    @GetMapping("/projects/{projectId}")
    public String showProject(@PathVariable("projectId") Long id, Model model, HttpSession session){
        if(session.getAttribute("UserId") == null){
            return "redirect:/";
        }
        model.addAttribute("project", projectServ.findOneProject(id));
        model.addAttribute("id", session.getAttribute("UserId"));
        return "show.jsp";
    }

    @GetMapping("/projects/edit/{id}")
    public String editProject(@PathVariable("id")Long id, @ModelAttribute("editProject") Project project, Model model, HttpSession session){
        if(session.getAttribute("UserId") == null){
            return "redirect:/";
        }
        Project aProject = projectServ.findOneProject(id);
        if(!session.getAttribute("UserId").equals(aProject.getUser().getId())){
            return "redirect:/logout";
        }
        model.addAttribute("editProject", aProject);
        return "editProject.jsp";
    }

    @PutMapping("/projects/edit/{id}")
    public String processProject(@PathVariable("id")Long id, @Valid @ModelAttribute("editProject") Project project, BindingResult result, HttpSession session){
        if(result.hasErrors()){
            return "editProject.jsp";
        }
        Project oneProject = projectServ.findProjectById(id);
        if(!session.getAttribute("UserId").equals(oneProject.getUser().getId())){
            return "redirect:/logout";
        }
        projectServ.addOrUpdateProject(project,(Long) session.getAttribute("UserId"));
        return "redirect:/home";
    }

    @DeleteMapping("/projects/{id}/delete")
    public String deleteProject(@PathVariable("id")Long id,HttpSession session){
        Project oneProject = projectServ.findProjectById(id);
        if(!session.getAttribute("UserId").equals(oneProject.getUser().getId())){
            return "redirect:/logout";
        }
        projectServ.deleteProject(id);
        return "redirect:/home";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
