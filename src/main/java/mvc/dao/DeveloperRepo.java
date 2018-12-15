package mvc.dao;

import mvc.model.Developer;
import mvc.util.ConnectionUtil;


import java.util.List;

public class DeveloperRepo implements GenericRepository<Developer, Integer>{
    private ConnectionUtil connectionUtil = new ConnectionUtil();

    @Override
    public void save(Developer developer) {

    }

    @Override
    public Developer getById(Integer integer) {
        return null;
    }

    @Override
    public void update(Developer developer) {

    }

    @Override
    public List<Developer> getAll() {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
