package mvc.service;

import mvc.repository.ProjectRepoImpl;
import mvc.model.Project;

import java.util.List;

public class ProjectService {
    private ProjectRepoImpl projectRepoImpl = new ProjectRepoImpl();

    public void saveToProjectRepo(Project project) {
        projectRepoImpl.save(project);
    }

    public Project getByIdFromProjectRepo(Integer id) {
        Project project = projectRepoImpl.getById(id);
        return project;
    }

    public void updateToProjectRepo(Project project) {
        projectRepoImpl.update(project);
    }

    public List<Project> getAllFromProjectRepo() {
        List<Project> projects = projectRepoImpl.getAll();
        return projects;
    }

    public void deleteFromProjectRepo(Integer id) {
        projectRepoImpl.delete(id);
    }

}
