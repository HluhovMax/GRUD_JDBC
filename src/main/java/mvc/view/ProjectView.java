package mvc.view;

import mvc.controller.ProjectController;
import mvc.model.Project;

import java.util.List;
import java.util.Scanner;

public class ProjectView {
    private ProjectController projectController = new ProjectController();
    private Scanner intScanner = new Scanner(System.in);
    private Scanner stringScanner = new Scanner(System.in);
    private Project project = new Project();

    public void save() {
        System.out.println("enter project name, cost:");
        project.setProject(stringScanner.nextLine());
        project.setCost(intScanner.nextInt());
        if (project != null) {
            projectController.save(project);
        }
    }

    public Project getById() {
        System.out.println("enter id:");
        project = projectController.getById(intScanner.nextInt());
        if (project != null) {
            System.out.println(project);
            return project;
        }
        return null;
    }

    public void update() {
        System.out.println("enter id, project, cost");
        project.setId(intScanner.nextInt());
        project.setProject(stringScanner.nextLine());
        project.setCost(intScanner.nextInt());
        if (project != null) {
            projectController.update(project);
        }
    }

    public List<Project> getAll() {
        List<Project> projects = projectController.getAll();
        if (projects != null) {
            for (Project project : projects
            ) {
                System.out.println(project);
            }
            return projects;
        }
        return null;
    }

    public void delete() {
        System.out.println("enter id:");
        projectController.delete(intScanner.nextInt());
    }
}
