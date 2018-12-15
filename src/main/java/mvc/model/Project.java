package mvc.model;

public class Project {
    private int id;
    private String project;
    private int cost;

    public Project() {
    }

    public Project(int id, String project, int cost) {
        this.id = id;
        this.project = project;
        this.cost = cost;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", project='" + project + '\'' +
                ", cost=" + cost +
                '}';
    }
}
