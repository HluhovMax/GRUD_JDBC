package mvc.dao;


import mvc.model.Skill;
import mvc.util.ConnectionUtil;


import java.util.List;

public class SkillRepo implements GenericRepository<Skill, Integer>{
    private ConnectionUtil connectionUtil = new ConnectionUtil();

    @Override
    public void save(Skill skill) {

    }

    @Override
    public Skill getById(Integer integer) {
        return null;
    }

    @Override
    public void update(Skill skill) {

    }

    @Override
    public List<Skill> getAll() {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
