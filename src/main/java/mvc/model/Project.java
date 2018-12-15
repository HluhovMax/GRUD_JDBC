package mvc.model;

public class Project {
    private int id;
    private String project;

    public Project() {
    }

    public Project(int id, String project) {
        this.id = id;
        this.project = project;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", project='" + project + '\'' +
                '}';
    }
}
