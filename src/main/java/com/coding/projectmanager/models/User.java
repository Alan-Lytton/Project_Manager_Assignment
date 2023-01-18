package com.coding.projectmanager.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message="First name is required!")
    @Size(min=3, max=30, message="First name must be between 3 and 30 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name may contain only letters.")
    private String firstName;

    @NotEmpty(message="Last name is required!")
    @Size(min=3, max=30, message="Last name must be between 3 and 30 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name may contain only letters.")
    private String lastName;

    @NotEmpty(message="Email is required!")
    @Email(message="Please enter a valid email!")
    private String email;

    @NotEmpty(message="Password is required!")
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least 1 upper case letter.")
    @Pattern(regexp = ".*[a-z].*", message = "Password must contain at least 1 lower case letter.")
    @Pattern(regexp = ".*[0-9].*", message = "Password must contain at least 1 number 0-9.")
    @Pattern(regexp = ".*[`~!@#$%^&*()\\\\-_=+\\\\\\\\|\\\\[{\\\\]};:'\\\",<.>/?].*", message = "Password must contain at least 1 special character.")
    private String password;

    @Transient
    @NotEmpty(message="Confirm Password is required!")
    @Size(min=8, max=128, message="Confirm Password must be between 8 and 128 characters")
    private String confirm;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Project> projects;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "teams",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> teamProjects;

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Project> getTeamProjects() {
        return teamProjects;
    }

    public void setTeamProjects(List<Project> teamProjects) {
        this.teamProjects = teamProjects;
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }



}


