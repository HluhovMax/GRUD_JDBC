package mvc.dao;

import mvc.model.Developer;
import mvc.model.Skill;
import mvc.util.ConnectionUtil;

import java.sql.SQLException;
import java.util.List;

public class SkillRepo implements GenericRepository<Skill, Integer>{
    private ConnectionUtil connectionUtil = new ConnectionUtil();

    @Override
    public void save(Skill skill) throws SQLException {

    }

    @Override
    public Skill getById(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public void update(Skill skill) throws SQLException {

    }

    @Override
    public List<Skill> getAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer integer) throws SQLException {

    }
}
