package mvc.model;


import java.util.List;

public class Project {
    private int id;
    private String project;
    private Company company;
    private List<Developer> developers;

    public Project() {
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", project='" + project + '\'' +
                ", company=" + company +
                ", developers=" + developers +
                '}';
    }
}
