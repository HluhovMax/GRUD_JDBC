package mvc.controller;

import mvc.model.Project;
import mvc.service.ProjectService;

import java.util.List;

public class ProjectController {
    private ProjectService projectService = new ProjectService();

    public void save(Project project) {
        projectService.saveToProjectRepo(project);
    }

    public Project getById(Integer id){
        Project project = projectService.getByIdFromProjectRepo(id);
        return project;
    }

    public void update(Project project){
        projectService.updateToProjectRepo(project);
    }

    public List<Project> getAll() {
        List<Project> projects = projectService.getAllFromProjectRepo();
        return projects;
    }

    public void delete(Integer id){
        projectService.deleteFromProjectRepo(id);
    }
}
