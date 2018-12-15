package mvc.dao;

import mvc.model.Developer;
import mvc.util.ConnectionUtil;

import java.sql.SQLException;
import java.util.List;

public class DeveloperRepo implements GenericRepository<Developer, Integer>{
    private ConnectionUtil connectionUtil = new ConnectionUtil();

    @Override
    public void save(Developer developer) throws SQLException {

    }

    @Override
    public Developer getById(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public void update(Developer developer) throws SQLException {

    }

    @Override
    public List<Developer> getAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer integer) throws SQLException {

    }
}
