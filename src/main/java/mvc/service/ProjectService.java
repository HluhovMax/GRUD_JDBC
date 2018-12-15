package mvc.service;

import mvc.dao.ProjectRepo;
import mvc.model.Project;

import java.util.List;

public class ProjectService {
    private ProjectRepo projectRepo = new ProjectRepo();

    public void saveToProjectRepo(Project project) {
        projectRepo.save(project);
    }

    public Project getByIdFromProjectRepo(Integer id) {
        Project project = projectRepo.getById(id);
        return project;
    }

    public void updateToProjectRepo(Project project) {
        projectRepo.update(project);
    }

    public List<Project> getAllFromProjectRepo() {
        List<Project> projects = projectRepo.getAll();
        return projects;
    }

    public void deleteFromProjectRepo(Integer id) {
        projectRepo.delete(id);
    }
}
