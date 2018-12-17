package mvc.model;

import java.util.List;

public class Customer {
    private int id;
    private String customer;
    private List<Project> projects;

    public Customer() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customer='" + customer + '\'' +
                ", projects=" + projects +
                '}';
    }
}
