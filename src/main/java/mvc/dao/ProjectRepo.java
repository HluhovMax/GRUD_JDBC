package mvc.dao;

import mvc.model.Project;
import mvc.util.ConnectionUtil;

import java.sql.SQLException;
import java.util.List;

public class ProjectRepo implements GenericRepository<Project, Integer>{
    private ConnectionUtil connectionUtil = new ConnectionUtil();

    @Override
    public void save(Project project) throws SQLException {

    }

    @Override
    public Project getById(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public void update(Project project) throws SQLException {

    }

    @Override
    public List<Project> getAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer integer) throws SQLException {

    }
}
