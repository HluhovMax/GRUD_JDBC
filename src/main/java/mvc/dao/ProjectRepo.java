package mvc.dao;

import mvc.model.Project;
import mvc.util.ConnectionUtil;


import java.util.List;

public class ProjectRepo implements GenericRepository<Project, Integer>{
    private ConnectionUtil connectionUtil = new ConnectionUtil();

    @Override
    public void save(Project project) {

    }

    @Override
    public Project getById(Integer integer) {
        return null;
    }

    @Override
    public void update(Project project) {

    }

    @Override
    public List<Project> getAll() {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
